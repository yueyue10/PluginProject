package com.zyj.plugin.home.news;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.mvvm.BaseMvvmActivity;
import com.zyj.plugin.common.uitl.recyclerv.HorizontalDividerItemDecoration;
import com.zyj.plugin.home.R;
import com.zyj.plugin.home.R2;
import com.zyj.plugin.home.mvvm.factory.HomeViewModelFactory;
import com.zyj.plugin.home.mvvm.viewmodel.NewsListViewModel;
import com.zyj.plugin.home.utils.JudgeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewsListActivity extends BaseMvvmActivity<NewsListViewModel> {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    NewsListAdapter newsListAdapter;
    List<NewsBean> mNewsBeans;

    @Override
    public int onBindLayout() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.informations));
    }

    @Override
    public void initRecyclerView() {
        mNewsBeans = new ArrayList<>();
        newsListAdapter = new NewsListAdapter(R.layout.item_news_list, mNewsBeans);
        initRecyclerView(recyclerView, newsListAdapter, new LinearLayoutManager(mActivity),
                new HorizontalDividerItemDecoration.Builder(this)
                        .colorResId(R.color.divider_news_list)
                        .sizeResId(R.dimen.dp_10).build());
    }

    @Override
    public void initListener() {
        newsListAdapter.setOnItemClickListener((adapter, view, position) -> {
                    int views = mNewsBeans.get(position).getViews();
                    mNewsBeans.get(position).setViews(views + 1);
                    newsListAdapter.notifyDataSetChanged();
                    JudgeUtils.startNewsDetailAc(mActivity, mNewsBeans.get(position).getId());
                }
        );
    }

    @Override
    public void initData() {
        mViewModel.getNewsList(6);
    }

    @Override
    public Class<NewsListViewModel> onBindViewModel() {
        return NewsListViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return HomeViewModelFactory.getInstance(getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getNewsListSingleLiveEvent().observe(this, new Observer<List<NewsBean>>() {
            @Override
            public void onChanged(@Nullable List<NewsBean> newsBeans) {
                mNewsBeans.clear();
                mNewsBeans.addAll(newsBeans);
                newsListAdapter.notifyDataSetChanged();
            }
        });
    }
}
