package vimmone.blog.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import okhttp3.Call;
import okhttp3.Response;
import vimmone.blog.Activity.LoginActivity;
import vimmone.blog.pojo.UserMessage;
import vimmone.blog.utils.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * @autor Vimmone
 */

public class UserFunction {
    private Context context;
    public UserFunction(Context context) {
        this.context = context;
    }

    public  void showDialog(final UserMessage userMessage) {

        new QMUIBottomSheet.BottomListSheetBuilder(context)//初始化QMUIBottomSheet。BottomListSheetBuilder获取实例
                .addItem("保存个人信息")
                .addItem("注销账号")
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {//item点击监听
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if(position==0) {//保存个人信息
                            OkGo.post(Constants.HOSTIP+"/modifyUserMessageByPhone")
                                    .params("uid", userMessage.getUid())
                                    .params("name", userMessage.getName())
                                    .params("avatar", userMessage.getAvatar())
                                    .params("state", userMessage.getState())
                                    .tag(this)
                                    .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            Toast.makeText(context, "保存成功..." , Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onError(Call call, Response response, Exception e) {
                                            super.onError(call, response, e);
                                            Toast.makeText(context, "保存失败，请重试..." , Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                        if(position==1) {//注销
                            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SHARE_NAME, MODE_PRIVATE).edit();
                            editor.clear();
                            editor.commit();
                            Intent intent = new Intent(context,LoginActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    }

                })
                .build()
                .show();


    }


}
