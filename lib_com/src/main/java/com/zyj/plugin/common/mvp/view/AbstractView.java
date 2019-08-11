package com.zyj.plugin.common.mvp.view;

import android.app.Activity;

/**
 * Description: <AbstractView><br>
 * Author:      mxdl<br>
 * Date:        2018/3/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface AbstractView extends ILoadView, IHintView, INoDataView, INetErrView {
    void initView();

    void initListener();

    void initData();

    void finishActivity();

    Activity getActivityContext();
}
