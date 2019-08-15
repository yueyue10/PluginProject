package com.zyj.plugin.common.mvvm.model;

import io.reactivex.disposables.Disposable;

/**
 * Description: <IBaseModel><br>
 * Author:      mxdl<br>
 * Date:        2019/06/30<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface IBaseModel {
    void addSubscribe(Disposable disposable);

    void onCleared();
}
