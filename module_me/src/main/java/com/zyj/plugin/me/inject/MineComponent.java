package com.zyj.plugin.me.inject;

import android.app.Application;

import com.zyj.plugin.common.data.module.HttpModule;
import com.zyj.plugin.login.inject.CommonBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        HttpModule.class,
        MineBindingModule.class,
        CommonBindingModule.class,
        AndroidSupportInjectionModule.class,})
public interface MineComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        MineComponent build();
    }
}