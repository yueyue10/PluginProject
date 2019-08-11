package com.zyj.plugin.me.order.detail;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.KeyValueBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.data.bean.WxPayResult;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxBus;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;
import com.zyj.plugin.common.uitl.ConverterUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Presenter
 */
public class OrderDetailPresenter extends BasePresenter<OrderDetailContract.View> implements OrderDetailContract.Presenter {

    private DataManager dataManager;

    @Inject
    OrderDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(OrderDetailContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(WxPayResult.class)
                .filter(wxPayResult -> wxPayResult != null)
                .subscribe(wxPayResult -> getOrderDetail(wxPayResult.getOrderCode())));
    }

    public List<KeyValueBean> getKeyValueData(OrderDetailBean orderDetailBean, String carNum, String parkName, String startTime, String endTime, String orderNum, String orderTime) {
        startTime = startTime.substring(0, startTime.lastIndexOf(":"));
        endTime = endTime.substring(0, endTime.lastIndexOf(":"));
        String startEndTime = startTime + "   /   " + endTime;
        String orderInfo = null;
        if (orderDetailBean.getOrderState() == 4) {
            orderInfo = String.format("订单编号：%s\n下单日期：%s\n支付方式：%s", orderNum, orderTime, "微信");
        } else {
            orderInfo = String.format("订单编号：%s\n下单日期：%s", orderNum, orderTime);
        }
        List<KeyValueBean> keyValueBeans = new ArrayList<>();
        keyValueBeans.add(new KeyValueBean("车牌号", ConverterUtils.convertCarNumStyle(carNum), 1));
        keyValueBeans.add(new KeyValueBean("停车场名称", parkName, 1));
        keyValueBeans.add(new KeyValueBean("起止时间", startEndTime, 1));
        keyValueBeans.add(new KeyValueBean("订单信息", orderInfo, 1));
        return keyValueBeans;
    }

    @Override
    public void getOrderDetail(String orderCode) {
        mView.showLoading();
        addSubscribe(dataManager.getOrderDetail(orderCode)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResultV2())
                .subscribeWith(new BaseObserver<OrderDetailBean>(mView) {
                    @Override
                    public void onNext(OrderDetailBean orderDetailBean) {
                        mView.hideLoading();
                        mView.getOrderDetailSuccess(orderDetailBean);
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
    public void getPayProcess(OrderDetailBean mOrderDetailBean, String payType) {

    }

    @Override
    public void checkOrderStatus(String orderId, int type) {
        addSubscribeV2(dataManager.checkOrderStatus(orderId, type), new BaseObserver<Boolean>(mView) {
            @Override
            public void onNext(Boolean value) {
                mView.hideLoading();
                mView.getPayProcessSuccess();
            }
        });
    }

    @Override
    public void confirmPirceZeroOrder(OrderDetailBean mOrderDetailBean) {

    }

}