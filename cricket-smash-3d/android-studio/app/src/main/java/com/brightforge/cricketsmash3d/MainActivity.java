package com.brightforge.cricketsmash3d;
import android.app.Activity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
public class MainActivity extends Activity {
 private WebView game;
 private static final String HOME="https://appassets.androidplatform.net/assets/index.html";
 @Override @SuppressLint("SetJavaScriptEnabled") protected void onCreate(Bundle state){
  super.onCreate(state);
  getWindow().setStatusBarColor(Color.rgb(7,24,38));
  getWindow().setNavigationBarColor(Color.rgb(7,24,38));
  game=new WebView(this);
  game.setBackgroundColor(Color.rgb(7,24,38));
  WebSettings settings=game.getSettings();
  settings.setJavaScriptEnabled(true);
  settings.setDomStorageEnabled(true);
  settings.setAllowFileAccess(false);
  settings.setAllowContentAccess(false);
  settings.setJavaScriptCanOpenWindowsAutomatically(false);
  settings.setSupportMultipleWindows(false);
  settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
  settings.setDefaultTextEncodingName("UTF-8");
  settings.setMediaPlaybackRequiresUserGesture(true);
  game.setWebChromeClient(new WebChromeClient());
  game.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest req){return !HOME.equals(req.getUrl().toString());}
   @Override public WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest req){
    if(HOME.equals(req.getUrl().toString()))try{
     InputStream source=getAssets().open("index.html");
     return new WebResourceResponse("text/html","UTF-8",source);
    }catch(IOException ignored){}
    return new WebResourceResponse("text/plain","UTF-8",404,"Not Found",Collections.emptyMap(),new ByteArrayInputStream(new byte[0]));
   }
  });
  setContentView(game);
  game.loadUrl(HOME);
 }
 @Override public void onBackPressed(){
  if(game!=null)game.evaluateJavascript("window.CricketSmashTest && window.CricketSmashTest.home()",null);
  else super.onBackPressed();
 }
 @Override protected void onPause(){if(game!=null)game.onPause();super.onPause();}
 @Override protected void onResume(){super.onResume();if(game!=null)game.onResume();}
 @Override protected void onDestroy(){if(game!=null){game.destroy();game=null;}super.onDestroy();}
}