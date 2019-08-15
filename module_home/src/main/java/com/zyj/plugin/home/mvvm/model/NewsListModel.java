package com.zyj.plugin.home.mvvm.model;

import android.app.Application;

import com.zyj.plugin.common.data.ApiService;
import com.zyj.plugin.common.data.RetrofitManager;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvvm.model.BaseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description: <NewsListModel><br>
 * Author:      mxdl<br>
 * Date:        2019/5/28<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class NewsListModel extends BaseModel {

    private ApiService apiService;

    public NewsListModel(Application application) {
        super(application);
        apiService = RetrofitManager.getInstance().getApiService();
    }

    public Observable<List<NewsBean>> getNewsList(int scenicId) {
        return apiService.getNewsList(scenicId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult());
    }
}
