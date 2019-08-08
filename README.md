# PluginProject

Android组件化探索

根据github开源框架来创建：https://github.com/mxdldev/android-mvp-mvvm-flytour

### 构建过程记录：

- 1.gradle里面的控件版本统一在config.gradle里面配置
- 2.在lib_com模块的dependencies里面依赖一些常用的第三方库、在具体的module里面再依赖具体的第三方库
- 3.在app、module_main、module_me里面依赖lib_com
- 4.在app里面配置项目的applicationId，并且配置Application，但是没用其他功能
- 5.在module_main、module_me里面编写界面等，包名是项目applicationId+对应的模块名，但是不配置Application