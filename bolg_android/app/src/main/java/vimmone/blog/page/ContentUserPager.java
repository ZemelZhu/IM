package vimmone.blog.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;
import vimmone.blog.Activity.LoginActivity;
import vimmone.blog.Activity.NoteDetailActivity;
import vimmone.blog.R;
import vimmone.blog.base.BasePager;
import vimmone.blog.pojo.UserMessage;
import vimmone.blog.services.UserFunction;
import vimmone.blog.utils.Constants;
import vimmone.blog.utils.Dialog;


public class ContentUserPager extends BasePager {
    public UserMessage userMessage;
    private TextView userName;
    private TextView userEmail;
    private RelativeLayout user_message;
    private TextView user_self_introduce;
    public ImageView userImage;
    private TextView userLovalId;
    private LinearLayout user_content;
    private View view;

    public ContentUserPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        user_function.setVisibility(View.VISIBLE);
        //1.设置标题
        SharedPreferences userData = context.getSharedPreferences(Constants.SHARE_NAME, Context.MODE_PRIVATE);
        String userId = userData.getString(Constants.SHARE_USER,null);
        if(userId==null) {
            Toast.makeText(context, "获取用户数据失败，请重新登陆" , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,LoginActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
            return ;
        }
        initView();
        getUserMessage(userId);

        final UserFunction userFunction = new UserFunction(context);
        user_function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userMessage.setName(userName.getText().toString());
                userMessage.setState(user_self_introduce.getText().toString());
               userFunction.showDialog(userMessage);
            }
        });
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ((Activity) context).startActivityForResult(intent, 1);
            }
        });

        user_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击进入我的评论
                Intent intent = new Intent(context,NoteDetailActivity.class);
                intent.putExtra("url",Constants.VIEW_HOST+"/usermessage/"+userMessage.getUid());
                System.out.println(userMessage.getUid());
                context.startActivity(intent);
            }
        });

    }

    private void initView() {
        view = View.inflate(context, R.layout.user_setting, null);
        userName = (TextView) view.findViewById(R.id.user_name);
        userEmail= (TextView) view.findViewById(R.id.user_email);
        user_message = (RelativeLayout) view.findViewById(R.id.user_message);
        user_self_introduce = (TextView) view.findViewById(R.id.user_self_introduce);
        user_content = (LinearLayout) view.findViewById(R.id.user_content);
        userImage = (ImageView) view.findViewById(R.id.user_image);
        userLovalId = (TextView) view.findViewById(R.id.user_id);
        tv_title.setText("个人中心");
    }

    private void getUserMessage(String userId) {
        OkGo.post(Constants.HOSTIP+"/getUserMessageByPhone")
                .params(Constants.SHARE_USER, userId)
                .tag(this).cacheKey("getUserMessageByPhone")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        // s 即为所需要的结果
                        Gson gson = new Gson();
                        userMessage = gson.fromJson(s, UserMessage.class);

                        userImage.setImageURI(Uri.parse(userMessage.getAvatar()));
                        userName.setText(userMessage.getName());
                        userEmail.setText("邮箱:"+userMessage.getEmail());
                        user_self_introduce.setText(userMessage.getState());
                        userLovalId.setText("账号:"+userMessage.getUid());
                        userName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Dialog.showDialog(context, "修改昵称", userName);

                            }
                        });
                        user_self_introduce.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               Dialog.showDialog(context, "修改个人介绍",user_self_introduce);

                            }
                        });
                        fl_content.addView(view);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(context, "联网获取用户数据失败..." , Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
