package com.zyj.plugin.home.news.detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zyj.plugin.common.data.bean.CommonContent;
import com.zyj.plugin.common.view.JustifyTextView;
import com.zyj.plugin.home.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author quchao
 * @date 2018/2/24
 */
public class NewsDetailAdapter extends BaseQuickAdapter<CommonContent, NewsDetailAdapter.ViewHolder> {


    public NewsDetailAdapter(int layoutResId, @Nullable List<CommonContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, CommonContent item) {
        if (helper.getAdapterPosition() == 0)
            helper.space_view.setVisibility(View.VISIBLE);
        switch (item.getType()) {
            case "title"://标题
                helper.news_title_tv.setText(item.getValue());
                helper.news_title_tv.setVisibility(View.VISIBLE);
                helper.news_iv.setVisibility(View.GONE);
                helper.news_content_tv.setVisibility(View.GONE);
                break;
            case "content"://文字内容
                helper.news_content_tv.setText(item.getValue());
                helper.news_content_tv.setVisibility(View.VISIBLE);
                helper.news_iv.setVisibility(View.GONE);
                helper.news_title_tv.setVisibility(View.GONE);
                break;
            case "img"://图片
                helper.news_title_tv.setVisibility(View.GONE);
                helper.news_content_tv.setVisibility(View.GONE);
                helper.news_iv.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .asBitmap()
                        .load(item.getValue())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                                int width = bitmap.getWidth();
                                int height = bitmap.getHeight();
                                Glide.with(mContext).load(bitmap)
                                        .apply(new RequestOptions()
                                                .skipMemoryCache(true)
                                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                                        .into(helper.news_iv);
                                setViewHeight(helper.news_iv, width, height);
                            }
                        });

                break;
        }
        helper.addOnClickListener(R.id.news_iv);
    }

    public void setViewHeight(View view, int widthPesent, int heightPesent) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = (dm.widthPixels - DensityUtil.dp2px(32));
        int newHeight = mScreenWidth * heightPesent / widthPesent;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = mScreenWidth;
        layoutParams.height = newHeight;
        view.setLayoutParams(layoutParams);
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.space_view)
        Space space_view;
        @BindView(R.id.news_title_tv)
        TextView news_title_tv;
        @BindView(R.id.news_content_tv)
        JustifyTextView news_content_tv;
        @BindView(R.id.news_iv)
        ImageView news_iv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
