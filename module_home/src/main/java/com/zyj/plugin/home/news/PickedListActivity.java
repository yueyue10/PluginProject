package com.zyj.plugin.home.news;

import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.home.R;

public class PickedListActivity extends NewsListActivity {

    @Override
    public void initView() {
        setTitle(getString(R.string.home_picked_news_title));
        initRecyclerView();
    }

    @Override
    public void initData() {
        showLoading();
        mPresenter.getHomeData(6);
    }

    @Override
    public void getHomeDataSuccess(HomeBean homeAdBean) {
        this.newsBeans.clear();
        this.newsBeans.addAll(homeAdBean.getStrategyList());
        newsListAdapter.notifyDataSetChanged();
    }
}
