package com.brightforge.arcadiaultra;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import java.io.InputStream;
import java.io.IOException;
public class MainActivity extends Activity {
  private WebView web;
  private static final String ASSET_HOST="appassets.androidplatform.net";
  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    getWindow().setStatusBarColor(0xff050917);
    getWindow().setNavigationBarColor(0xff050917);
    web=new WebView(this);web.setBackgroundColor(0xff050917);setContentView(web);
    WebSettings s=web.getSettings();
    s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);
    s.setAllowFileAccess(false);s.setAllowContentAccess(false);
    s.setJavaScriptCanOpenWindowsAutomatically(false);
    web.setWebChromeClient(new WebChromeClient());
    web.setWebViewClient(new WebViewClient(){
      @Override public WebResourceResponse shouldInterceptRequest(WebView view,WebResourceRequest request){
        android.net.Uri u=request.getUrl();
        if(!"https".equals(u.getScheme()) || !ASSET_HOST.equals(u.getHost()))
          return new WebResourceResponse("text/plain","UTF-8",403,"Denied",java.util.Collections.emptyMap(),new java.io.ByteArrayInputStream(new byte[0]));
        if(!"/assets/index.html".equals(u.getPath()))
          return new WebResourceResponse("text/plain","UTF-8",404,"Not Found",java.util.Collections.emptyMap(),new java.io.ByteArrayInputStream(new byte[0]));
        try{return new WebResourceResponse("text/html","UTF-8",getAssets().open("index.html"));}
        catch(IOException e){return new WebResourceResponse("text/plain","UTF-8",null);}
      }
      @Override public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest request){return true;}
    });
    web.loadUrl("https://appassets.androidplatform.net/assets/index.html");
  }
  @Override public void onBackPressed(){
    if(web!=null)web.evaluateJavascript("if(window.__ARCADIA_TEST){window.__ARCADIA_TEST.home()}else{history.back()}",null);
    else super.onBackPressed();
  }
  @Override protected void onDestroy(){if(web!=null){web.destroy();web=null;}super.onDestroy();}
}
