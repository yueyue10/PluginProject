package com.zyj.plugin.home.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zyj.plugin.common.data.bean.HomeAdBean;
import com.zyj.plugin.home.R;

import java.util.List;

public class HomePagerAdapter extends PagerAdapter {

    private List<HomeAdBean> homeAdBeans;
    private Context mContext;

    public HomePagerAdapter(Context mContext, List<HomeAdBean> homeAdBeans) {
        this.mContext = mContext;
        this.homeAdBeans = homeAdBeans;
    }

    @Override
    public int getCount() {
        return homeAdBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        Glide.with(mContext).load(homeAdBeans.get(position).getAdImg()).apply(new RequestOptions()
                .error(R.mipmap.pic_home_default_banner).placeholder(R.mipmap.pic_home_default_banner)).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(v -> onClickListener.onClick(homeAdBeans.get(position)));
        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public OnItemClickListener onClickListener;

    public interface OnItemClickListener {
        void onClick(HomeAdBean homeAdBean);
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
