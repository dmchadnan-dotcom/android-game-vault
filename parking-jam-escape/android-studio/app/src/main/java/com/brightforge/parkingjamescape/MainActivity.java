package com.brightforge.parkingjamescape;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsets;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/** Offline-only game host. No internet permission, analytics, or third-party SDKs. */
public final class MainActivity extends Activity {
  private WebView game;
  private static final String BASE = "https://appassets.androidplatform.net/assets/index.html";

  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    getWindow().setStatusBarColor(Color.rgb(7,15,25));
    getWindow().setNavigationBarColor(Color.rgb(7,15,25));
    FrameLayout frame = new FrameLayout(this);
    frame.setBackgroundColor(Color.rgb(7,15,25));
    frame.setOnApplyWindowInsetsListener((v, inset) -> {
      if (Build.VERSION.SDK_INT >= 30) {
        android.graphics.Insets bars = inset.getInsets(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
        v.setPadding(bars.left,bars.top,bars.right,bars.bottom);
      } else {
        v.setPadding(inset.getSystemWindowInsetLeft(),inset.getSystemWindowInsetTop(),inset.getSystemWindowInsetRight(),inset.getSystemWindowInsetBottom());
      }
      return inset;
    });
    game = new WebView(this);
    game.setBackgroundColor(Color.rgb(7,15,25));
    WebSettings settings = game.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setDomStorageEnabled(true);
    settings.setBlockNetworkLoads(true);
    settings.setAllowContentAccess(false);
    settings.setAllowFileAccess(false);
    settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
    settings.setJavaScriptCanOpenWindowsAutomatically(false);
    settings.setSupportMultipleWindows(false);
    game.setWebChromeClient(new WebChromeClient());
    game.setWebViewClient(new WebViewClient(){
      @Override public boolean shouldOverrideUrlLoading(WebView v, android.webkit.WebResourceRequest r) { return !BASE.equals(r.getUrl().toString()); }
      @Override public boolean shouldOverrideUrlLoading(WebView v, String url) { return true; }
    });
    WebView.setWebContentsDebuggingEnabled(false);
    frame.addView(game,new FrameLayout.LayoutParams(-1,-1));
    setContentView(frame);
    try (InputStream in=getAssets().open("index.html"); ByteArrayOutputStream out=new ByteArrayOutputStream()) {
      byte[] buf=new byte[16384]; int n;
      while ((n=in.read(buf))!=-1)out.write(buf,0,n);
      game.loadDataWithBaseURL(BASE,out.toString(StandardCharsets.UTF_8.name()),"text/html","UTF-8",null);
    } catch (Exception e){
      game.loadDataWithBaseURL(BASE,"<h1>Could not open offline game</h1>","text/html","UTF-8",null);
    }
  }
  @Override public void onBackPressed() {
    if (game!=null){game.evaluateJavascript("window.__parkingAndroidBack && window.__parkingAndroidBack()",null);return;}
    super.onBackPressed();
  }
  @Override protected void onDestroy(){if(game!=null){game.stopLoading();game.destroy();game=null;}super.onDestroy();}
}
