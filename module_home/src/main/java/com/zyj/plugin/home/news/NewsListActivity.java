package com.zyj.plugin.home.news;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.common.uitl.recyclerv.HorizontalDividerItemDecoration;
import com.zyj.plugin.home.R;
import com.zyj.plugin.home.R2;
import com.zyj.plugin.home.utils.JudgeUtils;
import com.zyj.plugin.login.utils.LoginUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewsListActivity extends BaseMvpActivity<NewsListPresenter> implements NewsListContract.View {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    NewsListAdapter newsListAdapter;
    List<NewsBean> newsBeans;

    @Override
    public int onBindLayout() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.informations));
        initRecyclerView();
    }

    public void initRecyclerView() {
        newsBeans = new ArrayList<>();
        newsListAdapter = new NewsListAdapter(R.layout.item_news_list, newsBeans);
        initRecyclerView(recyclerView, newsListAdapter, new LinearLayoutManager(mActivity),
                new HorizontalDividerItemDecoration.Builder(this)
                        .colorResId(R.color.divider_news_list)
                        .sizeResId(R.dimen.dp_10).build());
    }

    @Override
    public void initListener() {
        newsListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.likes_layout) {
                LinearLayout likes_layout = view.findViewById(R.id.likes_layout);
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.like_news_list);
                likes_layout.startAnimation(anim);
                if (!LoginUtils.isLogin(mActivity, Constants.REQUEST_CODE_LOGIN) || newsBeans.get(position).getStates() != 2)
                    return;
                mPresenter.strategyInfoVote(newsBeans.get(position).getId(), position);
            }
        });
        newsListAdapter.setOnItemClickListener((adapter, view, position) -> {
                    int views = newsBeans.get(position).getViews();
                    newsBeans.get(position).setViews(views + 1);
                    newsListAdapter.notifyDataSetChanged();
                    JudgeUtils.startNewsDetailAc(mActivity, newsBeans.get(position).getId());
                }
        );
    }

    @Override
    public void initData() {
        showLoading();
        mPresenter.getNewsList(6);
    }

    @Override
    public void voteStrategySuccess(int position) {
        int status = newsBeans.get(position).getStates();
        int vote = newsBeans.get(position).getVote();
        switch (status) {
            case 1://已经点赞
                newsBeans.get(position).setVote(vote - 1);
                newsBeans.get(position).setStates(2);
                break;
            default://未点赞
                newsBeans.get(position).setVote(vote + 1);
                newsBeans.get(position).setStates(1);
                break;
        }
        newsListAdapter.notifyItemChanged(position);
    }

    @Override
    public void getHomeDataSuccess(HomeBean homeAdBean) {

    }

    @Override
    public void getNewsListSuccess(List<NewsBean> newsBeans) {
        this.newsBeans.clear();
        this.newsBeans.addAll(newsBeans);
        newsListAdapter.notifyDataSetChanged();
    }
}
