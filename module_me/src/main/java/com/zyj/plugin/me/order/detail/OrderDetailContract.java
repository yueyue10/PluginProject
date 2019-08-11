package com.zyj.plugin.me.order.detail;

import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.AbstractView;
import com.zyj.plugin.common.data.bean.OrderDetailBean;

public interface OrderDetailContract {

    interface View extends AbstractView {
        void getOrderDetailSuccess(OrderDetailBean orderBean);

        void cancelOrderSuccess();

        void getPayProcessSuccess();

        void showPayResult(Boolean isSuccess, OrderDetailBean mOrderDetailBean);

        void showPriceDifferent();
    }

    interface Presenter extends AbstractPresenter<View> {
        void getOrderDetail(String orderCode);

        void cancelOrder(String orderCode);

        void getPayProcess(OrderDetailBean mOrderDetailBean, String payType);

        void checkOrderStatus(String orderId, int type);

        void confirmPirceZeroOrder(OrderDetailBean mOrderDetailBean);
    }
}