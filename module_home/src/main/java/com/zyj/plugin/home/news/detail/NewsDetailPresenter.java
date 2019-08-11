package com.zyj.plugin.home.news.detail;

import android.support.v4.util.SparseArrayCompat;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.reflect.TypeToken;
import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.CommonContent;
import com.zyj.plugin.common.data.bean.NewsDetailBean;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Presenter
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailContract.View> implements NewsDetailContract.Presenter {

    private DataManager dataManager;
    public ArrayList<String> imgUrls;
    public SparseArrayCompat<Integer> content_img;

    @Inject
    NewsDetailPresenter(DataManager dataManager) {
        super(dataManager);
        imgUrls = new ArrayList<>();
        this.dataManager = dataManager;
        content_img = new SparseArrayCompat<>();
    }

    @Override
    public void getNewsDetail(int id) {
        addSubscribe(dataManager.getNewsDetail(id), new BaseObserver<NewsDetailBean>(mView) {
            @Override
            public void onNext(NewsDetailBean newsDetailBean) {
                newsDetailBean.setCommonContents(GsonUtils.fromJson(
                        newsDetailBean.getContent(), new TypeToken<ArrayList<CommonContent>>() {
                        }.getType()));
                for (int i = 0; i < newsDetailBean.getCommonContents().size(); i++) {
                    if ("content".equals(newsDetailBean.getCommonContents().get(i).getType())) {
                        newsDetailBean.setDescription(newsDetailBean.getCommonContents().get(i).getValue());
                        break;
                    }
                }
                initImgPosition(newsDetailBean.getCommonContents());
                mView.hideLoading();
                mView.getNewsDetailSuccess(newsDetailBean);
            }
        });
    }

    private void initImgPosition(List<CommonContent> commonContents) {
        int imgPosition = -1;
        for (int i = 0; i < commonContents.size(); i++) {
            if ("img".equals(commonContents.get(i).getType())) {
                imgPosition++;
                imgUrls.add(commonContents.get(i).getValue());
                content_img.put(i, imgPosition);
            }
        }
    }
}