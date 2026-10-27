package com.brightforge.parkingjamescape;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebResourceRequest;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
public final class MainActivity extends Activity {
  private WebView web;
  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    getWindow().setStatusBarColor(Color.rgb(10,18,37));
    getWindow().setNavigationBarColor(Color.rgb(10,18,37));
    web=new WebView(this);
    setContentView(web);
    WebSettings settings=web.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setDomStorageEnabled(true);
    settings.setAllowFileAccess(false);
    settings.setAllowContentAccess(false);
    settings.setJavaScriptCanOpenWindowsAutomatically(false);
    settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
    settings.setSupportZoom(false);
    web.setWebChromeClient(new WebChromeClient());
    web.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return !"appassets.androidplatform.net".equals(request.getUrl().getHost());
      }
    });
    try (InputStream input=getAssets().open("index.html"); ByteArrayOutputStream bytes=new ByteArrayOutputStream()) {
      byte[] buffer=new byte[8192]; int n;
      while((n=input.read(buffer))!=-1) bytes.write(buffer,0,n);
      String content=bytes.toString(StandardCharsets.UTF_8.name());
      web.loadDataWithBaseURL("https://appassets.androidplatform.net/assets/index.html",content,"text/html","UTF-8",null);
    } catch(Exception ex) {
      web.loadData("<h2>Could not load offline game.</h2>","text/html","UTF-8");
    }
  }
  @Override public void onBackPressed() {
    if(web!=null&&web.canGoBack()) web.goBack(); else super.onBackPressed();
  }
  @Override protected void onDestroy(){ if(web!=null){web.destroy();web=null;}super.onDestroy(); }
}
