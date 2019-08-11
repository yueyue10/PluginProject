package com.zyj.plugin.login.inject;

import android.app.Application;

import com.zyj.plugin.common.data.module.HttpModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        HttpModule.class,
        LoginBindingModule.class,
        AndroidSupportInjectionModule.class,})
public interface LoginComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        LoginComponent build();
    }
}