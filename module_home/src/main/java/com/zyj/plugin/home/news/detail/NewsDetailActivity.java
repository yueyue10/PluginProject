package com.zyj.plugin.home.news.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zyj.plugin.common.data.API;
import com.zyj.plugin.common.data.bean.CommonContent;
import com.zyj.plugin.common.data.bean.NewsDetailBean;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.home.R;
import com.zyj.plugin.home.utils.JudgeUtils;
import com.zyj.plugin.login.web.ShareFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseMvpActivity<NewsDetailPresenter> implements NewsDetailContract.View {

    HeaderView headerView;
    NewsDetailAdapter newsDetailAdapter;
    List<CommonContent> commonContents;
    ShareFragment shareFragment;
    NewsDetailBean newsDetailBean = new NewsDetailBean();
    private String share_url;
    int newsId;

    @Override
    public int onBindLayout() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView() {
        setTitleBack(getString(R.string.news_detail_title));
        setRight(R.mipmap.transmit);
        initRecyclerView();
    }

    private void initRecyclerView() {
        commonContents = new ArrayList<>();
        newsDetailAdapter = new NewsDetailAdapter(R.layout.item_news_detail, commonContents);
        newsDetailAdapter.addHeaderView(getHeaderView());
        initRecyclerView(R.id.recyclerView, newsDetailAdapter, new LinearLayoutManager(mActivity));
    }

    private View getHeaderView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.header_common_detail, null);
        headerView = new HeaderView(view);
        return view;
    }

    @OnClick(R.id.iv_right)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                if (shareFragment == null) {
                    shareFragment = ShareFragment.getInstance(newsDetailBean.getTitle(), newsDetailBean.getDescription(),
                            String.format(share_url, 1, newsDetailBean.getId()),
                            newsDetailBean.getCoverUrl());
                }
                shareFragment.show(getSupportFragmentManager(), "ShareFragment");
                break;
        }
    }

    @Override
    public void initListener() {
        newsDetailAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.news_iv)
                JudgeUtils.startShowPictureAc(mActivity, mPresenter.imgUrls, mPresenter.content_img.get(position));
        });
    }

    @Override
    public void initData() {
        showLoading();
        share_url = API.BASE_URL + "mlf/InformationDetail.html?type=%d&id=%d";
        newsId = getIntent().getIntExtra("newsId", 0);
        mPresenter.getNewsDetail(newsId);
    }

    @Override
    public void getNewsDetailSuccess(NewsDetailBean newsDetailBean) {
        this.newsDetailBean = newsDetailBean;
        headerView.bindData(newsDetailBean);
        commonContents.addAll(newsDetailBean.getCommonContents());
        newsDetailAdapter.notifyDataSetChanged();
    }

    class HeaderView {
        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.time_tv)
        TextView time_tv;

        public HeaderView(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindData(NewsDetailBean newsDetailBean) {
            title_tv.setText(newsDetailBean.getTitle());
            time_tv.setText(newsDetailBean.getPublishTime());
        }
    }

}
