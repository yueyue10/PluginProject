package com.zyj.plugin.home.news.picture;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.photoview.PhotoView;
import com.zyj.plugin.home.R;

import java.util.List;

public class SamplePagerAdapter extends PagerAdapter {

    public Context context;
    public List<String> resList;

    public SamplePagerAdapter(Context context, List<String> list) {
        this.resList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return resList.size() > 0 ? resList.size() : null;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_show_picture, container, false);
        // 实例化一个ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.bind(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 给ViewPager的Item添加点击事件
    public OnPageClickListener onPageClickListener;

    public interface OnPageClickListener {
        void onPageClick(int position);
    }

    public void setOnPageClickListener(OnPageClickListener listener) {
        this.onPageClickListener = listener;
    }

    class ViewHolder {

        PhotoView display_iv;

        public ViewHolder(View view) {
            display_iv = view.findViewById(R.id.display_iv);
        }

        public void bind(final int position) {
            Glide.with(context).load(resList.get(position)).apply(new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher))
                    .into(display_iv);
        }
    }
}

