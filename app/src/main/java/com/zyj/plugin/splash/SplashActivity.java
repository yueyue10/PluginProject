package com.zyj.plugin.splash;

import android.content.Intent;

import com.zyj.plugin.R;
import com.zyj.plugin.common.mvp.BaseActivity;
import com.zyj.plugin.main.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashActivity extends BaseActivity {

    @Override
    public int onBindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> jumpToMain());
    }

    private void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
