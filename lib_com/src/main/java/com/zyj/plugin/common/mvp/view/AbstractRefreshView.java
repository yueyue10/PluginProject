package com.zyj.plugin.common.mvp.view;

import com.zyj.plugin.common.mvp.contract.BaseRefreshContract;

import java.util.List;

/**
 * Description: <AbstractRefreshView><br>
 * Author:      mxdl<br>
 * Date:        2018/2/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface AbstractRefreshView<T> extends BaseRefreshContract.View {
    //刷新数据
    void refreshData(List<T> data);
    //加载更多
    void loadMoreData(List<T> data);
}
