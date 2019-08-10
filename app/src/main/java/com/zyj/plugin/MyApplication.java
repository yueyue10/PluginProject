package com.zyj.plugin;

import com.zyj.plugin.common.BaseApplication;
import com.zyj.plugin.inject.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Description: <MyApplication><br>
 * Author:      mxdl<br>
 * Date:        2018/12/27<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //NewsDBManager.getInstance(this).initNewsDB();
//        RetrofitManager.init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
