package com.zyj.plugin.common.mvvm.view;

import java.util.ArrayList;

/**
 * Description: <IBaseRefreshView><br>
 * Author:      mxdl<br>
 * Date:        2019/6/30<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface IBaseRefreshView extends IBaseView {
    void stopRefreshView();

    void autoLoadData();

    void clearData(ArrayList<?>... lists);

    void configRefreshLayout();

    void configRefreshLayout(boolean enableRefresh, boolean enableLoadMore, boolean autoLoadMore);
}
