package debug;

import com.zyj.plugin.common.BaseApplication;
import com.zyj.plugin.login.inject.DaggerLoginComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Description: <><br>
 * Author:      mxdl<br>
 * Date:        2018/12/27<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class LoginApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerLoginComponent.builder().application(this).build();
    }

}
