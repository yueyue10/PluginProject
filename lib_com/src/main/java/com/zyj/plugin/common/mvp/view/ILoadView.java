package com.zyj.plugin.common.mvp.view;

/**
 * Description: <ILoadView><br>
 * Author:      mxdl<br>
 * Date:        2018/2/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface ILoadView {
    //显示初始加载的View，初始进来加载数据需要显示的View
    void showLoading();

    //隐藏初始加载的View
    void hideLoading();
}
