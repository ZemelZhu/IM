package vimmone.blog.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import vimmone.blog.R;
import vimmone.blog.utils.Constants;

/**
 * 初始化界面
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performCodeWithPermission("读取SD卡需要用到的权限", new PermissionCallback() {
            @Override
            public void hasPermission() {
            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharedPreferences userData = getSharedPreferences(Constants.SHARE_NAME, Context.MODE_PRIVATE);
                String userId = userData.getString(Constants.SHARE_USER,null);
                //你需要跳转的地方的代码
                if(userId==null) {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000); //延迟2秒跳转

    }

}
