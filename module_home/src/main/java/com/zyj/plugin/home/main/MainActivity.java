package com.zyj.plugin.home.main;

import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.home.R;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    @Override
    public int onBindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initStatusBar() {
    }
}
