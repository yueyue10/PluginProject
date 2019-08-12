package com.zyj.plugin.home.payol;

import com.zyj.plugin.common.data.bean.CarNumberInfoBean;
import com.zyj.plugin.common.data.bean.OnLinePayOrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.AbstractView;

import java.util.List;

public interface PayOnLineContract {
    interface View extends AbstractView {
        void showSaveClickAble();

        void showSaveUnClickAble();

        void showCarNumbers(List<CarNumberInfoBean> carNumberInfoBeans);

        void showFindFail();

        void showPayConfirm(OnLinePayOrderBean reseverPayOrder);

        void showNotifySelectPayType();

        void showPayResult(boolean isPaySuccess, OrderDetailBean orderDetailBean);

        void showWxPayResult(boolean isPaySuccess);

        void showPriceDifferent();
    }

    interface Presenter extends AbstractPresenter<View> {
        CarNumberInfoBean getCarNumber(int position);

        void initData();

        void toPay();

        void setWeChatPayMode(boolean isChecked);

        void setAlipayPayMode(boolean isChecked);

        void pay();

        void setShouldPayCarNumber(String carNumber);

        int getSelectPosition(String number);

        void createOrder(boolean isShowLoading);
    }
}