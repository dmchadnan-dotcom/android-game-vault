package com.brightforge.carromking3d;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Collections;

public class MainActivity extends Activity {
    private WebView game;
    private static final String ORIGIN = "appassets.androidplatform.net";
    @Override @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(7,19,35));
        getWindow().setNavigationBarColor(Color.rgb(7,19,35));
        game = new WebView(this);
        game.setBackgroundColor(Color.rgb(7,19,35));
        WebSettings ws=game.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setAllowFileAccess(false);
        ws.setAllowContentAccess(false);
        ws.setJavaScriptCanOpenWindowsAutomatically(false);
        ws.setMediaPlaybackRequiresUserGesture(true);
        ws.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        ws.setDefaultTextEncodingName("UTF-8");
        game.setWebChromeClient(new WebChromeClient());
        game.setWebViewClient(new WebViewClient() {
           @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest req) {
               return !ORIGIN.equals(req.getUrl().getHost());
           }
           @Override public WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest req) {
               if("https://appassets.androidplatform.net/assets/index.html".equals(req.getUrl().toString())) {
                  try {
                     InputStream input=getAssets().open("index.html");
                     return new WebResourceResponse("text/html","UTF-8",input);
                  } catch(IOException ignored) { }
               }
               return new WebResourceResponse("text/plain","UTF-8",404,"Not Found",Collections.emptyMap(),new ByteArrayInputStream(new byte[0]));
           }
        });
        setContentView(game);
        game.loadUrl("https://appassets.androidplatform.net/assets/index.html");
    }
    @Override protected void onPause(){if(game!=null)game.onPause();super.onPause();}
    @Override protected void onResume(){super.onResume();if(game!=null)game.onResume();}
    @Override protected void onDestroy(){if(game!=null){game.destroy();game=null;}super.onDestroy();}
}
