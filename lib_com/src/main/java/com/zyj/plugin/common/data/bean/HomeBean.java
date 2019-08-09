package com.zyj.plugin.common.data.bean;

import java.util.ArrayList;
import java.util.List;

public class HomeBean {

    private List<HomeAdBean> advertisementList;
    private List<NewsBean> strategyList;
    private NewsBean pickedNews;

    public List<HomeAdBean> getAdvertisementList() {
        if (advertisementList == null) {
            advertisementList = new ArrayList<>();
            advertisementList.add(new HomeAdBean());
        }
        return advertisementList;
    }

    public void setAdvertisementList(List<HomeAdBean> advertisementList) {
        this.advertisementList = advertisementList;
    }

    public List<NewsBean> getStrategyList() {
        if (strategyList == null) {
            return new ArrayList<>();
        }
        return strategyList;
    }

    public void setStrategyList(List<NewsBean> strategyList) {
        this.strategyList = strategyList;
    }

    public NewsBean getPickedNews() {
        if (strategyList != null && strategyList.size() > 0) {
            return strategyList.get(0);
        }
        return new NewsBean();
    }

    public void setPickedNews(NewsBean pickedNews) {
        this.pickedNews = pickedNews;
    }
}
