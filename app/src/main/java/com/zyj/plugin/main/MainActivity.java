package com.zyj.plugin.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.zyj.plugin.R;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.common.provider.ARouterConfig;
import com.zyj.plugin.common.provider.IHomeProvider;
import com.zyj.plugin.common.provider.IMeProvider;
import com.zyj.plugin.home.payol.PayOnLineActivity;
import com.zyj.plugin.login.utils.LoginUtils;

import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    public static final int RESVERSE_REQUEST_CODE = 1111;
    public static final int PAY_ON_LINE_REQUEST_CODE = 1112;

    @Autowired(name = ARouterConfig.HomeFragment)
    IHomeProvider mHomeProvider;

    @Autowired(name = ARouterConfig.MineFragment)
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
    public void enableTitleStatusBar() {

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
            }

            @Override
            public boolean onInterruptSelect(int index) {
                return false;
            }
        });
        middleView.setOnClickListener(v -> {
                    MainPopWindow mainPopWindow = new MainPopWindow(mActivity,
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//提示弹窗
                    mainPopWindow.setOnClickListener(positionTag -> {
                        switch (positionTag) {
                            case "booking_space"://预约车位
//                                if (LoginUtils.isLogin(mActivity,RESVERSE_REQUEST_CODE)) {
//                                    Intent intent_reserve = new Intent(MainActivity.this, ParkActivity.class);
//                                    startActivity(intent_reserve);
//                                }
                                break;
                            case "online_payment"://在线缴费
                                if (LoginUtils.isLogin(mActivity, PAY_ON_LINE_REQUEST_CODE)) {
                                    Intent intent_pay_online = new Intent(MainActivity.this, PayOnLineActivity.class);
                                    startActivity(intent_pay_online);
                                }
                                break;
                            case "reverse_for_car"://反向寻车
//                                if (LoginUtils.isLogin(mActivity,RESVERSE_REQUEST_CODE)) {
//                                    Intent intent_find_car = new Intent(MainActivity.this, FindCarActivity.class);
//                                    startActivity(intent_find_car);
//                                }
                                break;
                        }
                    });
                    mainPopWindow.showAtLocation(middleView, Gravity.BOTTOM, 0, 0);
                }
        );
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
