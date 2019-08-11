package com.zyj.plugin.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.zyj.plugin.common.R;


/**
 * 一个公共的经过处理后的webView，顶部具有进度条
 * <br>
 * 初始化时调用initView()
 */
public class WebViewBox extends LinearLayout {

    public WebView webview;
    public ProgressBar progressBar;
    boolean isInit = false;
    Context context;
    WebSettings webSettings;

    public WebViewBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public WebViewBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WebViewBox(Context context) {
        super(context);
        init(context);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_web_view, null);
        LayoutParams llp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view.setLayoutParams(llp);
        webview = view.findViewById(R.id.webView);
        progressBar = view.findViewById(R.id.progressBar);
        this.addView(view);
        initView();
    }


    /**
     * 初始化，做一些需要的处理
     */
    public void initView() {
        if (isInit)
            return;
        isInit = true;
        // 为WebView设置WebViewClient处理某些操作
        this.webview.setWebChromeClient(new MyWebChromeClient());
        webSettings = this.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存：
        webSettings.setDisplayZoomControls(false);//不显示缩放控制条
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBlockNetworkImage(false);
    }

    public void setTextSizeLarger() {
        webSettings.setTextSize(WebSettings.TextSize.LARGER);
    }

    public void setWebViewClient() {
        this.webview.setWebViewClient(new webViewClient());
    }

    public void loadUrl(String url) {
        webview.loadUrl(url);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(1);
    }

    public void loadData(String data) {
        webview.loadData(data, "text/html", "UTF-8");
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(1);
    }

    public boolean goBack() {
        if (webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return false;
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressBar.setVisibility(GONE);
            } else {
                if (progressBar.getVisibility() == GONE)
                    progressBar.setVisibility(VISIBLE);
                progressBar.setProgress(newProgress);
            }
            LogUtils.d("onProgressChanged datetimte:" + System.currentTimeMillis());
            super.onProgressChanged(view, newProgress);
        }
    }

    class webViewClient extends WebViewClient {
        // 重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.equals("")) {
                LogUtils.d("shouldOverrideUrlLoading-- loading url:" + url);
                webview.loadUrl(url);
                progressBar.setVisibility(View.VISIBLE);
            }
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            LogUtils.d("onReceivedSslError");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            LogUtils.d("web loader error-- errorCode:" + errorCode + "  description:" + description + " failingUrl:" + failingUrl);
            // 当加载异常时
            Toast.makeText(context, "加载异常，请稍后再试", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
            LogUtils.d("onPageFinished--url:" + url);
            super.onPageFinished(view, url);
        }
    }
}
