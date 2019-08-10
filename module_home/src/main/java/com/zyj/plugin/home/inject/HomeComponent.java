//package com.zyj.plugin.home.inject;
//
//import com.zyj.plugin.common.BaseApplication;
//import com.zyj.plugin.common.data.module.HttpModule;
//
//import javax.inject.Singleton;
//
//import dagger.Component;
//import dagger.android.AndroidInjectionModule;
//import dagger.android.support.AndroidSupportInjectionModule;
//
///**
// * 全局的单例的东西提到这里来，比如SP,DaoSession 等等 ！
// * <p>
// * Created by anylife.zlb@gmail.com on 2018/1/11.
// */
//@Singleton
//@Component(modules = {
//        HttpModule.class,         //全局的Module,要确保提供的对象是全局唯一的
//        AndroidInjectionModule.class,   //在应用程序的MainComponent（application 中inject了）中，注入AndroidInjectionModule，
//        // 以确保Android的类(Activity、Fragment、Service、BroadcastReceiver及ContentProvider等)可以绑定。
//        // 一般把AndroidInjectionModule放在ApplicationComponent中，其他的Component依赖Application即可
//        AndroidSupportInjectionModule.class,  //使用的Fragment 是V4 包中的？不然就只需要AndroidInjectionModule
//        HomeActivityModule.class,    //减少模版代码,需要依赖注入的只需要添加两行代码就好了
//})
//public interface HomeComponent {
//    void inject(BaseApplication application);
//}
