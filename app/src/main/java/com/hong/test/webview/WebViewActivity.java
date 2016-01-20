package com.hong.test.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.hong.test.R;

/**
 * @author Hong
 * @Name: WebViewActivity
 * @Package com.hong.test
 * @Description: ${todo}
 * @date 15/11/12
 * @time 上午9:58
 * @copyright 广州市金税信息系统集成有限公司
 */
public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private Button mBtnJS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
        initWebView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_js:
                // 用Android代码调用JavaScript函数：
                mWebView.loadUrl("javascript:myFunction()");
                break;
        }
    }

    public void initView() {
        mWebView = (WebView) findViewById(R.id.web_view);
        mBtnJS = (Button) findViewById(R.id.btn_js);

        mBtnJS.setOnClickListener(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        mWebView.addJavascriptInterface(new WebAppInterface(this), "myInterfaceName");
        mWebView.loadUrl("file:///android_asset/sample.html");

    }

    /**
     * 自定义的Android代码和JavaScript代码之间的桥梁类
     *
     * @author 1
     */
    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        // 如果target 大于等于API 17，则需要加上如下注解
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
        }
    }
}
