//package com.zyj.plugin.home.inject;
//
//import com.zyj.plugin.common.inject.BaseActivityComponent;
//import com.zyj.plugin.home.fragment.HomeFragment;
//
//import dagger.Module;
//import dagger.android.ContributesAndroidInjector;
//
///**
// * 全部放在这里来统一的管理 ！
// * 新建了一个Activity 的并且需要inject 的只需要添加两行代码
// * <p>
// * 大部分的页面都不需要再额外的提供对象的话只需要DefaultActivityModule 就好了，否则自定义XXActivityModule
// * <p>
// * <p>
// * 对个人而言，这样的好处在于：
// * 1.每次不再需要额外声明一个SubCompoent，再次减少模板代码
// * 2.每个Activity的Module都放在同一个AllActivitysModule中进行统一管理，每次修改只需要修改这一个类即可
// * 3.每个Activity所单独需要的依赖，依然由各自的Module进行管理和实例化，依然没有任何耦合
// */
////1111111111 subcomponent=BaseActivityComponent
//@Module(subcomponents = {BaseActivityComponent.class})
//public abstract class HomeActivityModule {
//
//    //2222222 新建了一个Activity 的并且需要inject 的只需要添加两行代码 DefaultActivityModule 适用于只要全局Module 中的内容
////    @ContributesAndroidInjector()
////    abstract HomeFragment homefragment();
//
//}
