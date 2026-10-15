package com.brightforge.ludoroyale;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.net.Uri;
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
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(7,20,25));
        getWindow().setNavigationBarColor(Color.rgb(7,20,25));
        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(7,20,25));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        settings.setSupportZoom(false);
        settings.setMediaPlaybackRequiresUserGesture(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            private WebResourceResponse empty() {
                return new WebResourceResponse("text/plain", "UTF-8", new ByteArrayInputStream(new byte[0]));
            }
            @Override public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                if (!"https".equalsIgnoreCase(url.getScheme()) || !HOST.equalsIgnoreCase(url.getHost())) return empty();
                String path = url.getPath();
                if (path == null || !path.startsWith("/assets/")) return empty();
                String asset = path.substring("/assets/".length());
                if (asset.contains("..") || asset.startsWith("/")) return empty();
                String mime = asset.endsWith(".html") ? "text/html" : "application/octet-stream";
                try { return new WebResourceResponse(mime, "UTF-8", getAssets().open(asset)); }
                catch (IOException ex) { return empty(); }
            }
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                return !("https".equalsIgnoreCase(url.getScheme()) && HOST.equalsIgnoreCase(url.getHost()));
            }
        });
        setContentView(webView);
        if (state == null) webView.loadUrl("https://" + HOST + "/assets/index.html");
        else webView.restoreState(state);
    }
    @Override @SuppressWarnings("deprecation") public void onBackPressed() {
        if (webView != null) webView.evaluateJavascript("window.ludoBack && window.ludoBack()",null);
        else super.onBackPressed();
    }
    @Override protected void onSaveInstanceState(Bundle out) {
        if (webView != null) webView.saveState(out);
        super.onSaveInstanceState(out);
    }
    @Override protected void onDestroy() {
        if (webView != null) { webView.destroy(); webView = null; }
        super.onDestroy();
    }
}
