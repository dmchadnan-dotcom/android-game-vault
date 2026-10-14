package com.brightforge.grandmasterarena;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MainActivity extends Activity {
  private WebView webView;
  private static final String HOST="appassets.androidplatform.net";
  private void immersive() {
    getWindow().getDecorView().setSystemUiVisibility(
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
      View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN|
      View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
  }
  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    immersive();
    webView=new WebView(this);
    webView.setBackgroundColor(Color.rgb(7,12,24));
    WebSettings s=webView.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setAllowFileAccess(false);
    s.setAllowContentAccess(false);
    s.setAllowFileAccessFromFileURLs(false);
    s.setAllowUniversalAccessFromFileURLs(false);
    s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
    s.setMediaPlaybackRequiresUserGesture(false);
    s.setSupportZoom(false);
    webView.setWebChromeClient(new WebChromeClient());
    webView.setWebViewClient(new WebViewClient() {
      private WebResourceResponse empty(){
        return new WebResourceResponse("text/plain","UTF-8",new ByteArrayInputStream(new byte[0]));
      }
      @Override public WebResourceResponse shouldInterceptRequest(WebView view,WebResourceRequest request){
        Uri u=request.getUrl();
        if(!"https".equalsIgnoreCase(u.getScheme())||!HOST.equalsIgnoreCase(u.getHost())) return null;
        String path=u.getPath();
        if(path==null||!path.startsWith("/assets/"))return empty();
        String asset=path.substring(8);
        if(asset.contains("..")||asset.startsWith("/"))return empty();
        String mime=asset.endsWith(".html")?"text/html":asset.endsWith(".js")?"application/javascript":"application/octet-stream";
        try{return new WebResourceResponse(mime,"UTF-8",getAssets().open(asset));}
        catch(IOException ex){return empty();}
      }
      @Override public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest request){
        Uri u=request.getUrl();
        return !("https".equalsIgnoreCase(u.getScheme())&&HOST.equalsIgnoreCase(u.getHost()));
      }
    });
    setContentView(webView);
    if(state==null)webView.loadUrl("https://"+HOST+"/assets/index.html");
    else webView.restoreState(state);
  }
  @Override public void onBackPressed() {
    if(webView!=null)webView.evaluateJavascript("if(window.chessBack){window.chessBack()}",null);
    else super.onBackPressed();
  }
  @Override public void onWindowFocusChanged(boolean hasFocus){super.onWindowFocusChanged(hasFocus);if(hasFocus)immersive();}
  @Override protected void onSaveInstanceState(Bundle b){if(webView!=null)webView.saveState(b);super.onSaveInstanceState(b);}
  @Override protected void onDestroy(){if(webView!=null)webView.destroy();super.onDestroy();}
}
