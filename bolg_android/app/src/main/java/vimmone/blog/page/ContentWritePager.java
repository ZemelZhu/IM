package vimmone.blog.page;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

import vimmone.blog.R;
import vimmone.blog.base.BasePager;
import vimmone.blog.fragment.ContentFragment;
import vimmone.blog.utils.Constants;

//未实现
public class ContentWritePager extends BasePager {

    private final ContentFragment contentFragment;
    public ContentWritePager(Context context, ContentFragment contentFragment) {
        super(context);
        this.contentFragment = contentFragment;
    }

    @Override
    public void initData() {
        super.initData();
        //1.设置标题
        tv_title.setText("写文章");
        View view = View.inflate(context, R.layout.write_content, null);
//        ScrollView agen_web =(ScrollView) view.findViewById(R.id.agen_web);



//        web.loadUrl(Constants.VIEW_HOST+"/editorTest");

        AgentWeb mAgentWeb = AgentWeb.with(contentFragment)//这里需要把 Activity 、 和 Fragment  同时传入
                .setAgentWebParent( (ViewGroup) view, new LinearLayout.LayoutParams(-1, -1))// 设置 AgentWeb 的父控件 ， 这里的view 是 LinearLayout ， 那么需要传入 LinearLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()//
                .go(Constants.VIEW_HOST+"/editorTest");


        fl_content.addView(view);
    }

    private class JsforAndroid {
        @JavascriptInterface
        public void userSignIn(String a,String b){
            System.out.println("322"+a+b);
        }
    }
}
