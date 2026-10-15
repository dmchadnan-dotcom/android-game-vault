package com.brightforge.cubecraft;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.graphics.Color;
import android.net.Uri;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
public class MainActivity extends Activity {
 private WebView web;
 private static final String ORIGIN="https://appassets.androidplatform.net/assets/";
 @Override public void onCreate(Bundle saved) {
  super.onCreate(saved);
  getWindow().setStatusBarColor(Color.rgb(8,14,28));
  getWindow().setNavigationBarColor(Color.rgb(8,14,28));
  web=new WebView(this);
  web.setBackgroundColor(Color.rgb(8,14,28));
  web.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
  WebSettings s=web.getSettings();
  s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);
  s.setAllowFileAccess(false);s.setAllowContentAccess(false);
  s.setAllowFileAccessFromFileURLs(false);s.setAllowUniversalAccessFromFileURLs(false);
  s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
  web.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest req){return !req.getUrl().toString().startsWith(ORIGIN);}
   @Override public WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest req) {
    Uri uri=req.getUrl();String full=uri.toString();
    if(!full.startsWith(ORIGIN)) return new WebResourceResponse("text/plain","UTF-8",403,"Forbidden",null,new ByteArrayInputStream(new byte[0]));
    String path=full.substring(ORIGIN.length()).split("\\?")[0];
    if(path.contains("..")||path.contains("\\")||path.startsWith("/")) return new WebResourceResponse("text/plain","UTF-8",403,"Forbidden",null,new ByteArrayInputStream(new byte[0]));
    try {
     InputStream stream=getAssets().open(path);
     String mime=path.endsWith(".html")?"text/html":path.endsWith(".js")?"application/javascript":path.endsWith(".css")?"text/css":"application/octet-stream";
     return new WebResourceResponse(mime,"UTF-8",stream);
    }catch(Exception e){return new WebResourceResponse("text/plain","UTF-8",404,"Not Found",null,new ByteArrayInputStream(new byte[0]));}
   }
  });
  setContentView(web);web.loadUrl(ORIGIN+"index.html");
 }
 @Override public void onBackPressed(){web.evaluateJavascript("window.CubeCraftApp&&window.CubeCraftApp.handleBack()",r->{if(!"true".equals(r))super.onBackPressed();});}
 @Override protected void onDestroy(){if(web!=null){web.destroy();web=null;}super.onDestroy();}
}