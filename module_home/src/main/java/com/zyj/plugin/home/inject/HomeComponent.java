//package com.zyj.plugin.home.inject;
//
//import android.app.Application;
//
//import com.zyj.plugin.common.data.module.HttpModule;
//
//import javax.inject.Singleton;
//
//import dagger.BindsInstance;
//import dagger.Component;
//import dagger.android.AndroidInjector;
//import dagger.android.DaggerApplication;
//import dagger.android.support.AndroidSupportInjectionModule;
//
//@Singleton
//@Component(modules = {
//        HttpModule.class,
//        HomeBindingModule.class,
//        AndroidSupportInjectionModule.class,})
//public interface HomeComponent extends AndroidInjector<DaggerApplication> {
//
//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        Builder application(Application application);
//
//        HomeComponent build();
//    }
//}