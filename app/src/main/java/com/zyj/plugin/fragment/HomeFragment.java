package com.zyj.plugin.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zyj.plugin.R;
import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.data.bean.ResourceBean;
import com.zyj.plugin.common.mvp.BaseMvpFragment;
import com.zyj.plugin.common.view.DotsLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {

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
        viewPager=view.findViewById(R.id.viewPager);
        dotLayout=view.findViewById(R.id.dotLayout);
        picked_news_iv=view.findViewById(R.id.picked_news_iv);
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
//                JudgeUtils.startNewsListAc(mActivity, "");
            } else if (resourceBeans.get(position).getName().equals(mActivity.getString(R.string.suggested_feedback))) {
//                startActivity(new Intent(mActivity, FeedbackActivity.class));
            }
        });
    }

    @Override
    public void getHomeDataSuccess(HomeBean homeAdBean) {
        this.homeBean = homeAdBean;
        vpSize = homeBean.getAdvertisementList().size();
        homePagerAdapter = new HomePagerAdapter(mActivity, homeBean.getAdvertisementList());
//        homePagerAdapter.setOnClickListener(homeAdBean -> {
//            if (StringUtils.isEmpty(homeAdBean.getAdUrl()))
//                return;
////            JudgeUtils.startWebViewAc(_mActivity, null,
////                    null, null, null, true);
//            JudgeUtils.startWebViewAc(_mActivity, homeAdBean.getAdUrl(), null);
//        });
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
