# PluginProject

Android组件化探索

根据github开源框架来创建：https://github.com/mxdldev/android-mvp-mvvm-flytour

### 构建过程记录：

- 1.gradle里面的控件版本统一在config.gradle里面配置
- 2.在lib_com模块的dependencies里面依赖一些常用的第三方库、在具体的module里面再依赖具体的第三方库
- 3.在app、module_home、module_me里面依赖lib_com
- 4.在app里面配置项目的applicationId，并且配置Application,壳的主界面在此module里面
- 5.在module_home、module_me里面编写界面等，包名是项目applicationId+对应的模块名，但是不配置Application和applicationId。
    只有在isModule为true的时候才会配置Application和applicationId。
- 6.测试dagger的发现：包含注解的fragment必须放在注解的activity里面才可以
- 7.组件化mvp框架构建完成。新加入自定义标题栏，以及在基类里面写好对标题栏的配置。对下拉刷新布局配置。
- 8.网络请求配置优化
