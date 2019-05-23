package vimmone.blog.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import vimmone.blog.R;


public class BasePager {

    /**
     * 上下文
     */
    public final Context context;//MainActivity

    /**
     * 视图，代表各个不同的页面
     */
    public View rootView;
    /**
     * 显示标题
     */
    public TextView tv_title;

    public ImageView user_function;
    /**
     * 加载各个子页面
     */
    public FrameLayout fl_content;



    public BasePager(Context context) {
        this.context = context;
        //构造方法一执行，视图就被初始化了
        rootView = initView();
    }

    /**
     * 用于初始化公共部分视图，并且初始化加载子视图的FrameLayout
     * @return
     */
    private View initView() {
        //基类的页面
        View view = View.inflate(context, R.layout.base_pager,null);
        System.out.println(view);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        user_function = (ImageView) view.findViewById(R.id.user_function);
        return view;
    }

    /**
     * 初始化数据;当孩子需要初始化数据;或者绑定数据;联网请求数据并且绑定的时候，重写该方法
     */
    public void initData(){

    }
}
