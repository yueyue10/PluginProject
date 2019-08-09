package com.zyj.plugin.common.inject;

import android.app.Application;

import com.zyj.plugin.common.BaseApplication;
import com.zyj.plugin.common.data.module.HttpModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {HttpModule.class, AndroidSupportInjectionModule.class,})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
