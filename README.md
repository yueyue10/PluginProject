# PluginProject

Android组件化探索 - mvp

参考[github开源框架][2]来创建


## 一、项目介绍



#### 项目配置
* 在project的gradle.properties里面配置【isModule】属性设置项目是单独模块还是整体工程。
#### 基础配置
* config.gradle配置所有gradle依赖的第三方库版本。
* 在project的build.gradle里面使用【apply from: "config.gradle"】引入config文件。
#### 关于butterknife使用的问题
* 在项目的build.gradle里面配置
    ```
    dependencies {
            classpath 'com.jakewharton:butterknife-gradle-plugin:9.0.0-rc2'
    ...
        }
    ```
* 在每个module的build.gradle里面配置
    ```
    apply plugin: 'com.jakewharton.butterknife'
    ```
    在dependencies中引用：
    ```
    annotationProcessor rootProject.ext.dependencies["butterknife-compiler"]
    ```
* 在BaseActivity和BaseFragment中使用ButterKnife.bind(this)进入注入; 
* 在Module里面的activity中使用@BindView(R2.id.recyclerView)进行使用。



## 二、项目Module介绍


### 1.app: 壳Module

![图片](http://m.qpic.cn/psb?/V11wANeJ2TddbC/OP8QUHTiXsDD4HOueodYR4lvtgdekF6WvcY.eP40xMg!/b/dLYAAAAAAAAA&bo=2gVSAwAAAAADB6w!&rf=viewer_4)

* 直接配置module为Application
* 依赖其他module模块
    ``` 
    implementation project(':lib_com')
    if (!isModule.toBoolean()) {
        implementation project(':module_home')
        implementation project(':module_me')
    } 
    ``` 
* APP壳界面，包括MainActivity和SplashActivity
* dagger注解配置网络请求HttpModule、其他module的Activity注册HomeBindingModule等。
    ```
    @Singleton
    @Component(modules = {
            HttpModule.class,
            AppBindingModule.class,
            HomeBindingModule.class,
            MineBindingModule.class,
            CommonBindingModule.class,
            AndroidSupportInjectionModule.class,})
    ```



### 2.module_home: Home模块

![图片](http://m.qpic.cn/psb?/V11wANeJ2TddbC/0I8HhKq4AN0f8r5gk0dAUCkC4aFTBEzaOVIrgX9vtWE!/b/dLYAAAAAAAAA&bo=iAWUAwAAAAADBzg!&rf=viewer_4)

* build.gradle里面通过配置下面的代码，使用公共的配置
```
apply from: "../module.build.gradle"
```
使用下面的代码配置工程为组件化模块。并设置Module的applicationId，依赖lib_com和module_com
```
if (isModule.toBoolean()) {
   applicationId "com.zyj.plugin.home"
}
```

* 在build.gradle里面配置
```
annotationProcessor rootProject.ext.dependencies["arouter-compiler"]
annotationProcessor rootProject.ext.dependencies["dagger-compiler"]
annotationProcessor rootProject.ext.dependencies["dagger-android-processor"]
annotationProcessor rootProject.ext.dependencies["butterknife-compiler"]
```
因为在lib_com里面的注解依赖在这里没用效果，所以单独配置。
* 在HomeBindingModule里面配置Dagger需要注入的Activity。
* 在HomeComponent里面配置Dagger需要注入的HttpModule和界面配置@Module。
* 在src/main/java/debug里面配置HomeApplication，使用HomeComponent注入。并且在src/main/module/AndroidManifest.xml里面注册HomeApplication，注册activity。配置好之后就可以在组件化模式下进行编译运行。
* 在src/main/AndroidManifest.xml里面注册activity，但不需要注册application。配置好之后就可以在app壳里面使用arouter进行跳转。




### 3.module_me: Mine模块

![图片](http://m.qpic.cn/psb?/V11wANeJ2TddbC/sX9N0akV3iCpBp8nqvCYlgxQMB69bh8P9cJNI9jPBA0!/b/dLgAAAAAAAAA&bo=hwVxAwAAAAADB9I!&rf=viewer_4)

配置同上面的module_home不再赘述。



### 4.module_com: 公共模块
* 在项目的module.build.gradle里面配置模块组件的build.gradle里面的公共内容。通过
```
if (isModule.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```
根据isModule的值设置module为library或者application。

![图片](http://m.qpic.cn/psb?/V11wANeJ2TddbC/FzJeKRMJLJXFx5O.dv9TUzYoF4ZZKFY2EjDnNY2fGh0!/b/dD4BAAAAAAAA&bo=fAVPAwAAAAADBxc!&rf=viewer_4)

* 公共模块的内容包括公共的界面，例如登录、webview界面
* 并提供CommonBindingModule在其他module里面引用
* 可以将此模块直接定义为library，这里为了单独测试登录在项目的gradle.properties里面配置inLogin标识进行修改。



### 5.lib_com: 基础库
![结构图](http://m.qpic.cn/psb?/V11wANeJ2TddbC/LTJP0PSJAdCBbwDQUE5asTkjBcWHO**tlNZ6sree0FI!/b/dMMAAAAAAAAA&bo=WwWsAwAAAAADB9M!&rf=viewer_4)
* 所有模块依赖的第三方库进行依赖
```
api rootProject.ext.dependencies["rxjava"]
api rootProject.ext.dependencies["rxandroid"]
    api rootProject.ext.dependencies["adapter-rxjava"]
    api rootProject.ext.dependencies["converter-gson"]
    api rootProject.ext.dependencies["logging-interceptor"]
...
```
* mvp框架的基类
* 工具类、自定义view、数据bean类
* router配置类



## 三、项目构建过程：

- 1.gradle里面的控件版本统一在config.gradle里面配置
- 2.在lib_com模块的dependencies里面依赖一些常用的第三方库、在具体的module里面再依赖具体的第三方库
- 3.在app、module_home、module_me里面依赖lib_com
- 4.在app里面配置项目的applicationId，并且配置Application,壳的主界面在此module里面
- 5.在module_home、module_me里面编写界面等，包名是项目applicationId+对应的模块名，但是不配置Application和applicationId。
    只有在isModule为true的时候才会配置Application和applicationId。
- 6.测试dagger的发现：包含注解的fragment必须放在注解的activity里面才可以
- 7.组件化mvp框架构建完成。新加入自定义标题栏，以及在基类里面写好对标题栏的配置。对下拉刷新布局配置。
- 8.网络请求配置优化



 [1]: http://naotu.baidu.com/file/e8e137ea34f8d792f1c5f35ef4dd4fee?token=0273fd3b891ed796
 [2]: https://github.com/mxdldev/android-mvp-mvvm-flytour
