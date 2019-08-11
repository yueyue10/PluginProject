package com.zyj.plugin.me.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zyj.plugin.common.provider.IMeProvider;
import com.zyj.plugin.me.fragment.MineFragment;

/**
 * Description: <NewProvider><br>
 * Author:      mxdl<br>
 * Date:        2019/5/23<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
@Route(path = "/mine/main", name = "我的")
public class MineProvider implements IMeProvider {

    @Override
    public void init(Context context) {

    }

    @Override
    public Fragment getMineFragment() {
        return MineFragment.newInstance();
    }
}
