package com.brightforge.snakeladdersroyale;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/** Offline, first-party HTML game shell. Requests to external sites are never needed. */
public final class MainActivity extends Activity {
  private WebView game;
  private static final String ORIGIN = "https://appassets.androidplatform.net/assets/index.html";

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setStatusBarColor(Color.rgb(8,21,34));
    getWindow().setNavigationBarColor(Color.rgb(8,21,34));
    getWindow().getDecorView().setSystemUiVisibility(0);
    FrameLayout frame = new FrameLayout(this);
    frame.setBackgroundColor(Color.rgb(8,21,34));
    // Android 15/16 enforces edge-to-edge for newer target SDKs: keep controls out of system bars.
    frame.setOnApplyWindowInsetsListener((view, insets) -> {
      if (Build.VERSION.SDK_INT >= 30) {
        android.graphics.Insets bars = insets.getInsets(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
        view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
      } else {
        view.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
      }
      return insets;
    });
    game = new WebView(this);
    game.setBackgroundColor(Color.rgb(8,21,34));
    WebSettings settings = game.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setDomStorageEnabled(true);
    settings.setAllowFileAccess(false);
    settings.setAllowContentAccess(false);
    settings.setJavaScriptCanOpenWindowsAutomatically(false);
    settings.setSupportMultipleWindows(false);
    settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
    settings.setBlockNetworkLoads(true);
    game.setWebChromeClient(new WebChromeClient());
    game.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) { return true; }
      @Override public boolean shouldOverrideUrlLoading(WebView view, android.webkit.WebResourceRequest req) { return !ORIGIN.equals(req.getUrl().toString()); }
    });
    WebView.setWebContentsDebuggingEnabled(false);
    frame.addView(game, new FrameLayout.LayoutParams(-1,-1));
    setContentView(frame);
    try (InputStream in = getAssets().open("index.html"); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      byte[] buffer = new byte[16384]; int count;
      while ((count = in.read(buffer)) != -1) out.write(buffer, 0, count);
      String html = out.toString(StandardCharsets.UTF_8.name());
      game.loadDataWithBaseURL(ORIGIN, html, "text/html", "UTF-8", null);
    } catch (Exception e) {
      game.loadDataWithBaseURL(ORIGIN, "<h1>Unable to start offline game</h1>", "text/html", "UTF-8", null);
    }
  }
  @Override protected void onDestroy() {
    if (game != null) { game.stopLoading(); game.destroy(); game = null; }
    super.onDestroy();
  }
}
