package com.zyj.plugin.me.fragment;

import com.blankj.utilcode.util.GsonUtils;
import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.NoPayOrderBean;
import com.zyj.plugin.common.data.bean.ResourceBean;
import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxBus;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;
import com.zyj.plugin.me.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {

    @Inject
    MinePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(MineContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(UserInfo.class)
                .subscribe(userInfo -> getUserInfo()));
    }

    @Override
    public void getUserInfo() {
        addSubscribe(dataManager.getUserInfo()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResultV2())
                .subscribeWith(new BaseObserver<UserInfo>(mView, false) {
                    @Override
                    public void onNext(UserInfo userInfo) {
                        SpManager.getInstance().putUserUserInfo(GsonUtils.toJson(userInfo));
                        mView.getUserInfoSuccess(userInfo);
                    }
                }));
    }

    @Override
    public void getNoPayInfo() {
        addSubscribe(dataManager.getNoPayInfo(SpManager.getInstance().getUserId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResultV2())
                .subscribeWith(new BaseObserver<List<NoPayOrderBean>>(mView) {
                    @Override
                    public void onNext(List<NoPayOrderBean> noPayOrderBeans) {
                        mView.getNoPayInfoSuccess(noPayOrderBeans.size());
                    }
                }));
    }

    public List<ResourceBean> getResourcesData() {
        List<ResourceBean> resourceBeans = new ArrayList<>();
        resourceBeans.add(new ResourceBean("我的订单", R.mipmap.orders));
        resourceBeans.add(new ResourceBean("在线缴费记录", R.mipmap.cars, 2));
        resourceBeans.add(new ResourceBean("推荐给好友", R.mipmap.recommend, 2));
        resourceBeans.add(new ResourceBean("常用信息", R.mipmap.fingd_cars, 1));
        resourceBeans.add(new ResourceBean("建议反馈", R.mipmap.icon_my_suggest, 1));
        resourceBeans.add(new ResourceBean("关于梦廊坊", R.mipmap.icon_my_about_dreamlf, 2));
        resourceBeans.add(new ResourceBean("设置", R.mipmap.set_up, 1));
        return resourceBeans;
    }
}

