package com.zyj.plugin.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zyj.plugin.common.mvp.BaseActivity;

@Route(path = "/home/mainAc")
public class MainActivity extends BaseActivity {
    @Override
    public int onBindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }
}
