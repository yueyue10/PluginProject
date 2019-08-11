package com.zyj.plugin.me.order;

import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.OrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.data.bean.WxPayResult;
import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxBus;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.presenter.BaseRefreshPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Presenter
 */
public class OrderListPresenter extends BaseRefreshPresenter<OrderListContract.View> implements OrderListContract.Presenter {

    private DataManager dataManager;

    @Inject
    OrderListPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(OrderListContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(OrderDetailBean.class)
                .filter(orderDetailBean -> orderDetailBean != null)
                .subscribe(locationData -> mView.cancelOrderSuccess()));
        addSubscribe(RxBus.getDefault().toFlowable(WxPayResult.class)
                .filter(wxPayResult -> wxPayResult != null)
                .subscribe(wxPayResult -> mView.getPayProcessSuccess()));
    }

    @Override
    public void getOrderList(int offset, int limit) {
        mView.showLoading();
        addSubscribe(dataManager.getOrderList(offset, limit, SpManager.getInstance().getUserId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResultV2())
                .subscribeWith(new BaseObserver<List<OrderBean>>(mView) {
                    @Override
                    public void onNext(List<OrderBean> orderBeans) {
                        mView.hideLoading();
                        mView.getOrderListSuccess(orderBeans);
                    }
                }));
    }

    @Override
    public void cancelOrder(String orderCode) {
        mView.showLoading();
        addSubscribe(dataManager.cancelOrder(orderCode)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResultV2())
                .subscribeWith(new BaseObserver<Boolean>(mView) {
                    @Override
                    public void onNext(Boolean integer) {
                        mView.hideLoading();
                        mView.cancelOrderSuccess();
                    }
                }));
    }

    @Override
    public void getPayProcess(OrderBean orderBean, String payType) {

    }

    @Override
    public void confirmPirceZeroOrder(OrderBean orderBean) {

    }

    @Override
    protected void getRefreshData(int offset) {
        getOrderList(offset, Constants.LIMIT);
    }
}