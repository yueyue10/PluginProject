package com.zyj.plugin.home.news;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.home.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends BaseQuickAdapter<NewsBean, NewsListAdapter.ViewHolder> {

    public NewsListAdapter(int layoutResId, @Nullable List<NewsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, NewsBean item) {
        if (helper.getAdapterPosition() == 0) {
            helper.shapeView.setVisibility(View.VISIBLE);
            helper.spaceView.setVisibility(View.GONE);
        } else {
            helper.shapeView.setVisibility(View.GONE);
            helper.spaceView.setVisibility(View.VISIBLE);
        }
        switch (item.getStates()) {
            case 1://已经点赞
                helper.likeIv.setImageResource(R.mipmap.thumbs_uped);
                break;
            default://未点赞
                helper.likeIv.setImageResource(R.mipmap.thumbs_up);
                break;
        }
        Glide.with(mContext).load(item.getCoverUrl())
                .apply(new RequestOptions().error(R.mipmap.ic_launcher))
                .into(helper.newsIv);
        helper.newsTitleTv.setText(item.getTitle());
        helper.newsTimeTv.setText(String.format("发布于%s", item.getPublishTime()));
        helper.newsSawNumTv.setText(item.getViewsStr());
        helper.newsLikedNumTv.setText(item.getVoteStr());
        helper.addOnClickListener(R.id.likes_layout);
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.shapeView)
        View shapeView;
        @BindView(R.id.spaceView)
        Space spaceView;
        @BindView(R.id.news_iv)
        ImageView newsIv;
        @BindView(R.id.news_title_tv)
        TextView newsTitleTv;
        @BindView(R.id.news_time_tv)
        TextView newsTimeTv;
        @BindView(R.id.news_saw_num_iv)
        ImageView newsSawNumIv;
        @BindView(R.id.news_saw_num_tv)
        TextView newsSawNumTv;
        @BindView(R.id.saw_layout)
        LinearLayout sawLayout;
        @BindView(R.id.like_iv)
        ImageView likeIv;
        @BindView(R.id.news_liked_num_tv)
        TextView newsLikedNumTv;
        @BindView(R.id.likes_layout)
        LinearLayout likesLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
