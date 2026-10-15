package com.brightforge.royalludo;
import android.app.Activity;
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
public class MainActivity extends Activity {
  private WebView web;
  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    web=new WebView(this);
    web.setBackgroundColor(0xFF09121F);
    web.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
    WebSettings s=web.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setAllowFileAccess(false);
    s.setAllowContentAccess(false);
    s.setJavaScriptCanOpenWindowsAutomatically(false);
    s.setSupportMultipleWindows(false);
    s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
    web.setWebChromeClient(new WebChromeClient());
    web.setWebViewClient(new WebViewClient(){
      @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest req){return true;}
      @Override public WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest req){
        if("appassets.androidplatform.net".equals(req.getUrl().getHost())&&"/assets/index.html".equals(req.getUrl().getPath())){
          try {InputStream asset=getAssets().open("index.html");return new WebResourceResponse("text/html","UTF-8",asset);}
          catch(IOException ignored){}
        }
        return new WebResourceResponse("text/plain","UTF-8",404,"Not Found",new java.util.HashMap<>(),new ByteArrayInputStream(new byte[0]));
      }
    });
    setContentView(web);
    web.loadUrl("https://appassets.androidplatform.net/assets/index.html");
  }
  @Override protected void onDestroy(){if(web!=null){web.destroy();web=null;}super.onDestroy();}
}
