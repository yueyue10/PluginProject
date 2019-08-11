package com.zyj.plugin.me.order;

import com.zyj.plugin.common.data.bean.OrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.AbstractView;

import java.util.List;

public interface OrderListContract {

    interface View extends AbstractView {

        void getOrderListSuccess(List<OrderBean> orderBeans);

        void cancelOrderSuccess();

        void getPayProcessSuccess();

        void showPayResult(Boolean isPaySuccess, OrderDetailBean orderDetailBean);

        void showPriceDifferent();

        void showPaySuccess();

        void showPayFail();
    }

    interface Presenter extends AbstractPresenter<View> {

        void getOrderList(int offset, int limit);

        void cancelOrder(String orderCode);

        void getPayProcess(OrderBean orderBean, String payType);


        void confirmPirceZeroOrder(OrderBean orderBean);
    }
}