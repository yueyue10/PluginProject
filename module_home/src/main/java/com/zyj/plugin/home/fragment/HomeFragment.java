package com.zyj.plugin.home.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.mvp.BaseMvpFragment;

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View  {

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int onBindLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public Activity getActivityContext() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public void getHomeDataSuccess(HomeBean homeAdBean) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showErrorMsg(String message) {

    }
}
