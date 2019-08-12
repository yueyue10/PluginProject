package com.zyj.plugin.home.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zyj.plugin.common.provider.ARouterConfig;
import com.zyj.plugin.common.provider.IHomeProvider;
import com.zyj.plugin.home.fragment.HomeFragment;

/**
 * Description: <NewProvider><br>
 * Author:      mxdl<br>
 * Date:        2019/5/23<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
@Route(path = ARouterConfig.HomeFragment, name = "首页")
public class HomeProvider implements IHomeProvider {

    @Override
    public void init(Context context) {

    }

    @Override
    public Fragment getHomeFragment() {
        return HomeFragment.newInstance();
    }
}
