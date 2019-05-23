package vimmone.blog.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import vimmone.blog.R;
import vimmone.blog.utils.Constants;

/*
*登陆界面
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebView webView = (WebView) findViewById(R.id.login_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        //前端调用android接口
        webView.addJavascriptInterface(new JsforAndroid(), Constants.WEB_ANDROID);
        webView.setWebViewClient(new WebViewClient() {
            //当加载页面完成的时候回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        webView.loadUrl(Constants.VIEW_HOST+"/signByPhone/inByMyApp");
    }

    private class JsforAndroid {
        @JavascriptInterface
        public void userSignIn(final String userId, final String password){
            //提供给前端的接口
            Toast.makeText(LoginActivity.this, "正在准备初始化,请等待..." , Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = getSharedPreferences(Constants.SHARE_NAME, MODE_PRIVATE).edit();
            editor.putString(Constants.SHARE_USER,userId);
            editor.putString(Constants.SHARE_PASSWORD,password);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
