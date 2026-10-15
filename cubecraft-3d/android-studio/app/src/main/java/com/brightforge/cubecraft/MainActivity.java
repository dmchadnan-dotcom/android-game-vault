package com.brightforge.cubecraft;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.graphics.Color;
import android.view.View;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import java.io.InputStream;

public class MainActivity extends Activity {
    private WebView view;
    private final String ORIGIN = "https://appassets.androidplatform.net/assets/";

    public final class AndroidBridge {
        @JavascriptInterface public void requestClose() { runOnUiThread(() -> finish()); }
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(8,14,28));
        getWindow().setNavigationBarColor(Color.rgb(8,14,28));
        view = new WebView(this);
        view.setBackgroundColor(Color.rgb(8,14,28));
        view.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebSettings s = view.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(false);
        s.setAllowContentAccess(false);
        s.setAllowFileAccessFromFileURLs(false);
        s.setAllowUniversalAccessFromFileURLs(false);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        view.addJavascriptInterface(new AndroidBridge(), "CubeCraftAndroid");
        view.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView web, WebResourceRequest req) {
                String uri = req.getUrl().toString();
                return !uri.startsWith(ORIGIN);
            }
            @Override public WebResourceResponse shouldInterceptRequest(WebView web, WebResourceRequest req) {
                Uri uri = req.getUrl();
                String full = uri.toString();
                if (!full.startsWith(ORIGIN)) return new WebResourceResponse("text/plain", "UTF-8", 403, "Forbidden", null, new java.io.ByteArrayInputStream(new byte[0]));
                String path = full.substring(ORIGIN.length()).split("\\?")[0];
                if (path.contains("..") || path.contains("\\") || path.startsWith("/")) return null;
                try {
                    InputStream stream = getAssets().open(path);
                    String mime = path.endsWith(".html") ? "text/html" : path.endsWith(".js") ? "application/javascript" : path.endsWith(".css") ? "text/css" : path.endsWith(".svg") ? "image/svg+xml" : "application/octet-stream";
                    return new WebResourceResponse(mime, "UTF-8", stream);
                } catch (Exception e) { return null; }
            }
        });
        setContentView(view);
        view.loadUrl(ORIGIN + "index.html");
    }
    @Override public void onBackPressed() {
        view.evaluateJavascript("window.CubeCraftApp&&window.CubeCraftApp.handleBack()", result -> {
            if (!"true".equals(result)) super.onBackPressed();
        });
    }
    @Override protected void onDestroy() { if (view != null) { view.destroy(); view = null; } super.onDestroy(); }
}
