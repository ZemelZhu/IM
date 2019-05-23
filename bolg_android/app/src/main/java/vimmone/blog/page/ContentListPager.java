package vimmone.blog.page;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import okhttp3.Call;
import okhttp3.Response;
import vimmone.blog.Activity.NoteDetailActivity;
import vimmone.blog.R;
import vimmone.blog.base.BasePager;
import vimmone.blog.pojo.Note;
import vimmone.blog.utils.Constants;

public class ContentListPager extends BasePager {

    private ListView blogList;
    private List<Note> rs = new ArrayList<Note>();
    private PtrFrameLayout mPtrFrame;

    public ContentListPager(Context context) {
        super(context);
        getData();
    }


    @Override
    public void initData() {
        super.initData();
        tv_title.setText("文章列表");
    }


    /**
     * 使用okhttp-OkGo联网请求数据
     */
    private void getDataFromNet() {

        OkGo.post(Constants.HOSTIP+"/getNoteByPageInPhone")     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("getNoteByPageInPhone")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)    // 缓存模式，详细请看缓存介绍
                //请求网络失败后读取缓存
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        // s 即为所需要的结果
                        Gson gson = new Gson();
                        rs.clear();
                        Type type = new TypeToken<ArrayList<Note>>() {
                        }.getType();
                        rs = gson.fromJson(s, type);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void getData() {

        View view = View.inflate(context, R.layout.content_list, null);
        LinearLayout loading = (LinearLayout) view.findViewById(R.id.content_loading);
        blogList = (ListView) view.findViewById(R.id.content_blog_list);
        mPtrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr);//下拉刷新插件
        loading.setVisibility(View.VISIBLE);

        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(context);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //当listview在第一个的时候才能下拉刷新
                if (blogList.getChildAt(0) != null) {
                    if (blogList.getChildAt(0).getTop() == 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                        getDataFromNet();
                        mPtrFrame.autoRefresh();//自动刷新
                    }
                }, 1800);
            }


        });

        blogList.setAdapter(new MyNewsAdapter());
        blogList.setOnItemClickListener(new MyOnItemClickListener());
        fl_content.addView(view);
        loading.setVisibility(View.INVISIBLE);
        mPtrFrame.autoRefresh();
    }

    /*
    listview点击事件监听
     */
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Note note = rs.get(i);
            Intent intent = new Intent(context, NoteDetailActivity.class);
            intent.putExtra("url", Constants.VIEW_HOST+"/articlePhone/" + note.getArticleId());
            context.startActivity(intent);

        }
    }

    private class MyNewsAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return rs.size();
        }

        @Override
        public Note getItem(int i) {
            return rs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View BlogView;
            ViewHolder viewHolder;
            if (view == null) {
                BlogView = View.inflate(context, R.layout.blogitem, null);

                viewHolder = new ViewHolder();
                viewHolder.noteTitle = (TextView) BlogView.findViewById(R.id.note_title);
                viewHolder.noteContent = (TextView) BlogView.findViewById(R.id.content);
                viewHolder.imageView = (SimpleDraweeView) BlogView.findViewById(R.id.note_img);
                BlogView.setTag(viewHolder); // 将ViewHolder存储在View中
            } else {
                BlogView = view;
                viewHolder = (ViewHolder) BlogView.getTag(); // 重新获取ViewHolder
            }


            Note note = getItem(i);
            //            noteImg.setImageUrlAndShow(note.getImgUrl());
            viewHolder.noteTitle.setText(note.getTitle());

            //加载图片和gif
            DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                    .setAutoPlayAnimations(true)
                    //加载drawable里的一张gif图
                    .setUri(Uri.parse(note.getImgUrl()))//设置uri
                    .build();
            //设置Controller
            viewHolder.imageView.setController(mDraweeController);

            viewHolder.noteContent.setText(note.getAbstractContent());
            return BlogView;
        }
    }
//listview优化
    class ViewHolder {
        TextView noteTitle;
        TextView noteContent;
        SimpleDraweeView imageView;
    }

}