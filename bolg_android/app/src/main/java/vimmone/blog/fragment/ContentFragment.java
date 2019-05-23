package vimmone.blog.fragment;

import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;

import vimmone.blog.R;
import vimmone.blog.base.BaseFragment;
import vimmone.blog.base.BasePager;
import vimmone.blog.page.ContentListPager;
import vimmone.blog.page.ContentUserPager;
import vimmone.blog.page.ContentWritePager;

import static vimmone.blog.R.id.contentlist;

/**
 * @autor Vimmone
 */

public class ContentFragment extends BaseFragment {
    private NoScrollViewPager viewpager;
    public ArrayList<BasePager> basePagers;
    private RadioGroup rg_main;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.content_fragment,null);
        viewpager = (NoScrollViewPager) view.findViewById(R.id.viewpager);
        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //初始化3个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new ContentListPager(context));//主页面
        basePagers.add(new ContentWritePager(context,this));//主页面
        basePagers.add(new ContentUserPager(context));//主页面
        //设置默认选中首页
        rg_main.check(contentlist);
        //设置ViewPager的适配器
        viewpager.setAdapter(new ContentFragmentAdapter(basePagers));

        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        /**
         *
         * @param group RadioGroup
         * @param checkedId 被选中的RadioButton的id
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case contentlist://主页radioButton的id
                    viewpager.setCurrentItem(0,false);
                    break;
                case R.id.contentwrite://新闻中心radioButton的id
                    viewpager.setCurrentItem(1,false);
                    break;

                case R.id.contentuser://设置中心RadioButton的id
                    viewpager.setCurrentItem(2,false);
                    break;
            }

        }
    }
}
