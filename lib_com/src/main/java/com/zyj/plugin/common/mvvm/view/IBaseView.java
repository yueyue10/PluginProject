package com.zyj.plugin.common.mvvm.view;

import android.app.Activity;
import android.content.Context;

/**
 * Description: <IBaseView><br>
 * Author:      mxdl<br>
 * Date:        2019/06/30<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface IBaseView extends IComView {
    void initView();

    void initListener();

    void initData();

    void finishActivity();

    void showInitLoadView(boolean show);

    void showNoDataView(boolean show);

    void showNetWorkErrView(boolean show);

    Activity getActivityContext();
}
