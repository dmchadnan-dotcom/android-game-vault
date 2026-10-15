package com.brightforge.bubblepopadventure;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Color;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Offline-only game host. No INTERNET permission. Local content lives under
 * a private https origin, which provides origin-scoped localStorage.
 * Outside navigation and all external requests are blocked.
 */
public final class MainActivity extends Activity {
    private static final String ORIGIN = "https://appassets.androidplatform.net";
    private WebView webView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(10, 16, 40));
        getWindow().setNavigationBarColor(Color.rgb(10, 16, 40));
        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(10, 16, 40));
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(false);
        s.setAllowContentAccess(false);
        s.setSupportZoom(false);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setJavaScriptCanOpenWindowsAutomatically(false);
        s.setMediaPlaybackRequiresUserGesture(true);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        webView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest request) {
                return !isAllowed(request.getUrl().toString());
            }
            @Override public WebResourceResponse shouldInterceptRequest(WebView v, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.equals(ORIGIN + "/assets/index.html")) {
                    try {
                        InputStream bytes = getAssets().open("index.html");
                        return new WebResourceResponse("text/html", "UTF-8", bytes);
                    } catch (IOException ignored) { return blocked(); }
                }
                return blocked();
            }
        });
        setContentView(webView);
        webView.loadUrl(ORIGIN + "/assets/index.html");
    }

    private static boolean isAllowed(String url) {
        return url.equals(ORIGIN + "/assets/index.html");
    }

    private static WebResourceResponse blocked() {
        WebResourceResponse response = new WebResourceResponse("text/plain", "UTF-8",
            new java.io.ByteArrayInputStream("Blocked".getBytes(StandardCharsets.UTF_8)));
        response.setStatusCodeAndReasonPhrase(403, "Forbidden");
        return response;
    }

    @Override @SuppressWarnings("deprecation") public void onBackPressed() {
        if (webView == null) { super.onBackPressed(); return; }
        webView.evaluateJavascript("window.BubbleApp && window.BubbleApp.handleBack()", value -> {
            if ("false".equals(value) || "null".equals(value)) finish();
        });
    }

    @Override protected void onDestroy() {
        if (webView != null) { webView.destroy(); webView = null; }
        super.onDestroy();
    }
}
