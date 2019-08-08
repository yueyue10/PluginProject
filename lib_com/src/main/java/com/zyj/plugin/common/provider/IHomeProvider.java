package com.zyj.plugin.common.provider;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Description: <IHomeProvider><br>
 * Author:      mxdl<br>
 * Date:        2019/5/23<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface IHomeProvider extends IProvider {
    Fragment getHomeFragment();
}
