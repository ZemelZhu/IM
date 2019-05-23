package vimmone.blog.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import vimmone.blog.R;

/**
 * webview加载
 */
public class NoteDetailActivity extends Activity  {

    private WebView webview;
    private ProgressBar pbLoading;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-08-24 09:43:47 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        webview = (WebView) findViewById(R.id.webview);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        findViews();
        pbLoading.setVisibility(View.VISIBLE);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            //当加载页面完成的时候回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbLoading.setVisibility(View.GONE);
            }
        });
        // 点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) { // 表示按返回键
                        // 时的操作
                        webview.goBack(); // 后退
                        // webview.goForward();//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });


        webview.loadUrl(getIntent().getStringExtra("url"));

    }




}
