package com.zyj.plugin.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.zyj.plugin.R;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.common.provider.IHomeProvider;
import com.zyj.plugin.common.provider.IMeProvider;

import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @Autowired(name = "/main/home")
    IHomeProvider mHomeProvider;

    @Autowired(name = "/main/mine")
    IMeProvider mMeProvider;

    JPTabBar tabBar;
    View middleView;
    private int mLastFgIndex;
    private Fragment mHomeFragment;
    private Fragment mMineFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public int onBindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    @Override
    public void initView() {
        tabBar = findViewById(R.id.tabBar);
        tabBar.setTitles("首页", "我的")
                .setNormalIcons(R.mipmap.home_page_m, R.mipmap.me)
                .setSelectedIcons(R.mipmap.home_page, R.mipmap.me_x)
                .generate();
        middleView = tabBar.getMiddleView();
        initTab();
        initListener();
        switchFragment(0);
    }

    private void initTab() {
        if (mHomeProvider != null) {
            mHomeFragment = mHomeProvider.getHomeFragment();
            mFragments.add(mHomeFragment);
        }
        if (mMeProvider != null) {
            mMineFragment = mMeProvider.getMineFragment();
            mFragments.add(mMineFragment);
        }
    }

    public void initListener() {
        tabBar.setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int index) {
                switchFragment(index);
//                ARouter.getInstance().build("/home/mainAc").navigation();
            }

            @Override
            public boolean onInterruptSelect(int index) {
                return false;
            }
        });
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commit();
            ft.add(R.id.frame_layout, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

}
