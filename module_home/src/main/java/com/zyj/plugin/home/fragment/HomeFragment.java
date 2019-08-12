package com.zyj.plugin.home.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zyj.plugin.common.data.bean.HomeAdBean;
import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.data.bean.ResourceBean;
import com.zyj.plugin.common.mvp.BaseMvpFragment;
import com.zyj.plugin.common.view.DotsLayout;
import com.zyj.plugin.home.R;
import com.zyj.plugin.home.R2;
import com.zyj.plugin.home.utils.JudgeUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {

    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    DotsLayout dotLayout;
    ImageView picked_news_iv;
    HomeModuleAdapter homeModuleAdapter;
    HomePagerAdapter homePagerAdapter;
    List<ResourceBean> resourceBeans;
    NewsBean newsBean = new NewsBean();
    HomeBean homeBean;
    int currentImg = 0;
    int vpSize = 0;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        initRecyclerView();
        dotLayout = view.findViewById(R.id.dotLayout);
        picked_news_iv = view.findViewById(R.id.picked_news_iv);
    }

    private void initRecyclerView() {
        resourceBeans = mPresenter.getResourcesData();
        homeModuleAdapter = new HomeModuleAdapter(R.layout.item_home_module, resourceBeans);
        initRecyclerView(R.id.module_rv, homeModuleAdapter, new GridLayoutManager(mActivity, 4));
    }

    @Override
    public void initData() {
        mPresenter.getHomeData(6);
    }

    @OnClick({R2.id.more_news_iv, R2.id.picked_news_iv})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.more_news_iv) {
            if (homeBean != null)
                JudgeUtils.startPickedListActivity(mActivity, homeBean.getStrategyList());
        } else if (i == R.id.picked_news_iv) {
            JudgeUtils.startNewsDetailAc(mActivity, newsBean.getId());
        }
    }

    @Override
    public void initListener() {
        viewPager.setOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                dotLayout.setDot(i, vpSize);
            }
        });
        homeModuleAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (resourceBeans.get(position).getName().equals(mActivity.getString(R.string.tickets))) {
                showToast(R.string.hint_building);
            } else if (resourceBeans.get(position).getName().equals(mActivity.getString(R.string.introduce))) {
//                startActivity(new Intent(mActivity, VenueListActivity.class));
            } else if (resourceBeans.get(position).getName().equals(mActivity.getString(R.string.informations))) {
                JudgeUtils.startNewsListAc(mActivity, "");
            } else if (resourceBeans.get(position).getName().equals(mActivity.getString(R.string.suggested_feedback))) {
//                startActivity(new Intent(mActivity, FeedbackActivity.class));
            }
        });
    }

    @Override
    public void getHomeDataSuccess(HomeBean homeBean) {
        this.homeBean = homeBean;
        vpSize = homeBean.getAdvertisementList().size();
        homePagerAdapter = new HomePagerAdapter(mActivity, homeBean.getAdvertisementList());
        homePagerAdapter.setOnClickListener(homeAdBean1 -> {
            if (StringUtils.isEmpty(homeAdBean1.getAdUrl()))
                return;
            JudgeUtils.startWebViewAc(mActivity, homeAdBean1.getAdUrl(), null);
        });
        dotLayout.setDot(0, vpSize);
        viewPager.setAdapter(homePagerAdapter);
        startLoop();
        //
        newsBean = homeBean.getPickedNews();
        Glide.with(mActivity).load(newsBean.getCoverUrl()).apply(new RequestOptions()
                .error(R.mipmap.pic_home_default_banner))
                .into(picked_news_iv);
    }

    /**
     * 开启循环
     */
    private void startLoop() {
        mPresenter.addRxBindingSubscribe(Observable.interval(1, 3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(aLong -> {
                    currentImg++;
                    if (currentImg >= homeBean.getAdvertisementList().size())
                        currentImg = 0;
                    viewPager.setCurrentItem(currentImg);
                }));
    }

}
