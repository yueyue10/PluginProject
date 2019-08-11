package com.zyj.plugin.inject;

import android.app.Application;

import com.zyj.plugin.common.data.module.HttpModule;
import com.zyj.plugin.home.inject.HomeBindingModule;
import com.zyj.plugin.me.inject.MineBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        HttpModule.class,
        AppBindingModule.class,
        HomeBindingModule.class,
        MineBindingModule.class,
        AndroidSupportInjectionModule.class,})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
