package vimmone.blog.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.store.CookieStore;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.Response;
import vimmone.blog.R;
import vimmone.blog.fragment.ContentFragment;
import vimmone.blog.page.ContentUserPager;
import vimmone.blog.utils.Constants;

public class MainActivity extends FragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_content);

        Fresco.initialize(this);
        initFragment();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    private void showImage(String imagePath) {
        OkGo.post(Constants.IMAGEHOSTIP+"/upload")//
                .tag(this)//
                .isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .params("myFile", new File(imagePath))   // 可以添加文件上传
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                        //获取图片控件修改图片
                        FragmentManager fm = getSupportFragmentManager();
                        ContentFragment fragmentByTag = (ContentFragment) fm.findFragmentByTag(MAIN_CONTENT_TAG);
                        ContentUserPager basePager = (ContentUserPager) fragmentByTag.basePagers.get(1);
                        basePager.userMessage.setAvatar(s.substring(8,s.length()-2));
                        basePager.userImage.setImageURI(Uri.parse(s.substring(8,s.length()-2)));
                        Toast.makeText(MainActivity.this,"图片更换成功，请保存",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        super.upProgress(currentSize, totalSize, progress, networkSpeed);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(MainActivity.this,"图片更换失败",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences userData = MainActivity.this.getSharedPreferences(Constants.SHARE_NAME, Context.MODE_PRIVATE);
        String userId = userData.getString(Constants.SHARE_USER,null);
        String password = userData.getString(Constants.SHARE_PASSWORD,null);

        OkGo.post(Constants.HOSTIP+"/userLoginByPhone")//
                .tag(this)//
                .params("uid",Integer.parseInt(userId))
                .params("password",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if(s.equals("error")) {
                            Toast.makeText(MainActivity.this,"用户登陆出现问题，请重新登陆",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
//                            Toast.makeText(MainActivity.this,"用户上线成功",Toast.LENGTH_SHORT).show();
                            CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
                            List<Cookie> allCookie = cookieStore.getAllCookie();
                            android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance();
                            for(Cookie cookie:allCookie) {
                                cookieManager.setCookie(Constants.HOST,cookie.toString());
                                System.out.println(allCookie.toString());
                            }

                        }

                    }
                });
    }

    private void initFragment() {
        //1.得到FragmentManger
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft= fm.beginTransaction();
        //3.替换
        ft.replace(R.id.main_content,new ContentFragment(), MAIN_CONTENT_TAG);//主页
        //4.提交
        ft.commit();




    }

}
