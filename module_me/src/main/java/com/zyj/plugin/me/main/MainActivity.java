package com.zyj.plugin.me.main;

import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.me.R;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    @Override
    public int onBindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public boolean enableToolbar() {
        return false;
    }
}
