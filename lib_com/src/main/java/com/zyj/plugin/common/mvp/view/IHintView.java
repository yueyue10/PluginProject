package com.zyj.plugin.common.mvp.view;

/**
 * Description: <ILoadView><br>
 * Author:      mxdl<br>
 * Date:        2018/2/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface IHintView {
    void showToast(String message);

    void showErrorMsg(String message);
}
