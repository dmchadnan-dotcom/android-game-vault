package com.brightforge.neonxoarena;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import java.io.InputStream;
import java.io.IOException;
public class MainActivity extends Activity {
 private WebView game;
 private static final String ORIGIN="https://appassets.androidplatform.net";
 @Override public void onCreate(Bundle state){
  super.onCreate(state);
  getWindow().setStatusBarColor(android.graphics.Color.rgb(10,18,40));
  getWindow().setNavigationBarColor(android.graphics.Color.rgb(10,18,40));
  game=new WebView(this);
  game.setBackgroundColor(android.graphics.Color.rgb(10,18,40));
  WebSettings s=game.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);
  s.setAllowFileAccess(false);s.setAllowContentAccess(false);s.setAllowFileAccessFromFileURLs(false);
  s.setAllowUniversalAccessFromFileURLs(false);s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);s.setCacheMode(WebSettings.LOAD_NO_CACHE);
  game.setWebViewClient(new WebViewClient(){
   @Override public WebResourceResponse shouldInterceptRequest(WebView view,WebResourceRequest request){
    if(request.getUrl().toString().equals(ORIGIN+"/assets/index.html")){
     try{InputStream data=getAssets().open("index.html");return new WebResourceResponse("text/html","UTF-8",data);}catch(IOException ignored){}
    }
    return new WebResourceResponse("text/plain","UTF-8",404,"Not Found",new java.util.HashMap<>(),new java.io.ByteArrayInputStream(new byte[0]));
   }
   @Override public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest request){return true;}
  });
  setContentView(game);game.loadUrl(ORIGIN+"/assets/index.html");
 }
 @Override protected void onDestroy(){if(game!=null){game.destroy();game=null;}super.onDestroy();}
}