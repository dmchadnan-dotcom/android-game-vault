package com.brightforge.gamevault;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Locale;
public class MainActivity extends Activity {
  private static final String HOST="appassets.androidplatform.net";
  private WebView browser;
  private void fullscreen() {
    getWindow().getDecorView().setSystemUiVisibility(
      View.SYSTEM_UI_FLAG_FULLSCREEN |
      View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
      View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
      View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
      View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
  }
  @Override public void onCreate(Bundle saved) {
    super.onCreate(saved);
    fullscreen();
    browser = new WebView(this);
    browser.setBackgroundColor(Color.rgb(7,12,24));
    WebSettings s=browser.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setAllowFileAccess(false);
    s.setAllowContentAccess(false);
    s.setAllowFileAccessFromFileURLs(false);
    s.setAllowUniversalAccessFromFileURLs(false);
    s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
    s.setMediaPlaybackRequiresUserGesture(false);
    s.setSupportZoom(false);
    browser.setWebChromeClient(new WebChromeClient());
    browser.setWebViewClient(new WebViewClient() {
      @Override public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest req) {
        Uri uri=req.getUrl();
        if(!"https".equalsIgnoreCase(uri.getScheme())||!HOST.equalsIgnoreCase(uri.getHost()))return null;
        String path=uri.getPath();
        if(path==null||!path.startsWith("/assets/"))return error();
        String asset=path.substring(8);
        if(asset.contains("..")||asset.startsWith("/"))return error();
        String ext=asset.substring(asset.lastIndexOf('.')+1).toLowerCase(Locale.ROOT);
        String mime;
        switch(ext) {
          case "html":mime="text/html";break;
          case "css":mime="text/css";break;
          case "js":mime="application/javascript";break;
          case "json":mime="application/json";break;
          case "svg":mime="image/svg+xml";break;
          case "png":mime="image/png";break;
          case "jpg":case "jpeg":mime="image/jpeg";break;
          case "webp":mime="image/webp";break;
          default:mime="application/octet-stream";
        }
        try {
          InputStream input=getAssets().open(asset);
          return new WebResourceResponse(mime,"UTF-8",input);
        } catch(IOException e){return error();}
      }
      private WebResourceResponse error(){return new WebResourceResponse("text/plain","UTF-8",new ByteArrayInputStream(new byte[0]));}
      @Override public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest req){
        Uri uri=req.getUrl();
        return !("https".equalsIgnoreCase(uri.getScheme())&&HOST.equalsIgnoreCase(uri.getHost()));
      }
    });
    setContentView(browser);
    if(saved!=null)browser.restoreState(saved);
    else browser.loadUrl("https://"+HOST+"/assets/index.html");
  }
  @Override public void onBackPressed(){if(browser.canGoBack())browser.goBack();else super.onBackPressed();}
  @Override public void onWindowFocusChanged(boolean focus){super.onWindowFocusChanged(focus);if(focus)fullscreen();}
  @Override protected void onSaveInstanceState(Bundle b){browser.saveState(b);super.onSaveInstanceState(b);}
  @Override protected void onDestroy(){if(browser!=null)browser.destroy();super.onDestroy();}
}
