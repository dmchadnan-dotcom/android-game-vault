package com.brightforge.tenfold;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private static final String HOST = "appassets.androidplatform.net";
    private WebView webView;
    private void immersive() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        immersive();
        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(9, 15, 28));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setSupportZoom(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            private WebResourceResponse empty() {
                return new WebResourceResponse("text/plain", "UTF-8",
                    new ByteArrayInputStream(new byte[0]));
            }
            @Override public WebResourceResponse shouldInterceptRequest(
                WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                if (!"https".equalsIgnoreCase(url.getScheme()) ||
                    !HOST.equalsIgnoreCase(url.getHost())) return null;
                String path = url.getPath();
                if (path == null || !path.startsWith("/assets/")) return empty();
                String asset = path.substring(8);
                if (asset.contains("..") || asset.startsWith("/")) return empty();
                String mime = "application/octet-stream";
                if (asset.endsWith(".html")) mime = "text/html";
                else if (asset.endsWith(".js")) mime = "application/javascript";
                else if (asset.endsWith(".css")) mime = "text/css";
                try {
                    return new WebResourceResponse(mime, "UTF-8", getAssets().open(asset));
                } catch (IOException ex) { return empty(); }
            }
            @Override public boolean shouldOverrideUrlLoading(
                WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                return !("https".equalsIgnoreCase(url.getScheme()) &&
                    HOST.equalsIgnoreCase(url.getHost()));
            }
        });
        setContentView(webView);
        if (state == null) webView.loadUrl("https://" + HOST + "/assets/index.html");
        else webView.restoreState(state);
    }
    @Override public void onBackPressed() {
        if (webView != null) {
            webView.evaluateJavascript("if (window.tenfoldBack) window.tenfoldBack();", null);
        } else super.onBackPressed();
    }
    @Override public void onWindowFocusChanged(boolean focused) {
        super.onWindowFocusChanged(focused);
        if (focused) immersive();
    }
    @Override protected void onSaveInstanceState(Bundle b) {
        if (webView != null) webView.saveState(b);
        super.onSaveInstanceState(b);
    }
    @Override protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}
