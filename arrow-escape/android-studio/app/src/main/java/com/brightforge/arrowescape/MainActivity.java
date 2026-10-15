package com.brightforge.arrowescape;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public final class MainActivity extends Activity {
 private WebView view;
 private static final String PAGE="https://appassets.androidplatform.net/assets/index.html";
 @Override public void onCreate(Bundle bundle){
  super.onCreate(bundle);
  getWindow().setStatusBarColor(Color.rgb(247,249,255));
  getWindow().setNavigationBarColor(Color.rgb(247,249,255));
  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
  view=new WebView(this);view.setBackgroundColor(Color.rgb(247,249,255));
  view.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
  WebSettings s=view.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);
  s.setAllowFileAccess(false);s.setAllowContentAccess(false);s.setSupportZoom(false);
  s.setJavaScriptCanOpenWindowsAutomatically(false);
  s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
  view.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView w,WebResourceRequest r){return !r.getUrl().toString().equals(PAGE);}
   @Override public WebResourceResponse shouldInterceptRequest(WebView w,WebResourceRequest r){
    if(!r.getUrl().toString().equals(PAGE))return blocked();
    try{return new WebResourceResponse("text/html","UTF-8",getAssets().open("index.html"));}catch(IOException e){return blocked();}
   }
  });
  setContentView(view);view.loadUrl(PAGE);
 }
 private static WebResourceResponse blocked(){
  WebResourceResponse response=new WebResourceResponse("text/plain","UTF-8",new ByteArrayInputStream("Blocked".getBytes(StandardCharsets.UTF_8)));
  response.setStatusCodeAndReasonPhrase(403,"Forbidden");return response;
 }
 @Override @SuppressWarnings("deprecation") public void onBackPressed(){
  if(view==null){super.onBackPressed();return;}
  view.evaluateJavascript("window.ArrowEscapeApp && window.ArrowEscapeApp.handleBack()",result->{if("false".equals(result)||"null".equals(result))finish();});
 }
 @Override public void onDestroy(){if(view!=null){view.destroy();view=null;}super.onDestroy();}
}