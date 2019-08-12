package com.zyj.plugin.home.payol;

import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.BaseResponseV2;
import com.zyj.plugin.common.data.bean.CarNumberInfoBean;
import com.zyj.plugin.common.data.bean.OnLinePayOrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.data.bean.PayInfoBean;
import com.zyj.plugin.common.data.bean.WxPayResult;
import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxBus;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Presenter
 */
public class PayOnLinePresenter extends BasePresenter<PayOnLineContract.View> implements PayOnLineContract.Presenter {

    private DataManager dataManager;
    private List<CarNumberInfoBean> carNumbers = new ArrayList<>();

    private boolean isWechatChecked = true;
    private boolean isAlipayChecked = false;
    private String payType = Constants.PAY_TYPE_WECHAT;
    private String carNumber;
    private OnLinePayOrderBean onLinePayOrder;

    @Inject
    PayOnLinePresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(PayOnLineContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(WxPayResult.class)
                .filter(wxPayResult -> wxPayResult != null)
                .subscribe(wxPayResult -> mView.showWxPayResult(wxPayResult.isPaySuccess())));
    }

    @Override
    public CarNumberInfoBean getCarNumber(int position) {
        return carNumbers.get(position);
    }

    @Override
    public void initData() {
        mView.showLoading();
        addSubscribeV2(dataManager.getCarNumberList(SpManager.getInstance().getUserId()), new BaseObserver<List<CarNumberInfoBean>>(mView) {
            @Override
            public void onNext(List<CarNumberInfoBean> carNumberInfoBeans) {
                mView.hideLoading();
                carNumbers.clear();
                carNumbers.addAll(carNumberInfoBeans);
                mView.showCarNumbers(carNumbers);
            }
        });
    }

    @Override
    public void toPay() {
        mView.showLoading();
        addSubscribe(dataManager.checkCarExist(carNumber)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponseV2>(mView) {

                    @Override
                    public void onNext(BaseResponseV2 baseResponseV2) {
                        if (baseResponseV2 != null && baseResponseV2.getResult() == BaseResponseV2.SUCCESS) {
                            boolean isCarExist = (boolean) baseResponseV2.getValue();
                            if (isCarExist) {
                                createOrder(false);
                            } else {
                                mView.hideLoading();
                                mView.showFindFail();
                            }
                        } else {
                            mView.hideLoading();
                            mView.showNetWorkErrView();
                        }
                    }
                }));
    }

    @Override
    public void createOrder(boolean isShowLoading) {
        if (isShowLoading) {
            mView.showLoading();
        }
        addSubscribeV2(dataManager.createOnLinePayOrder(String.valueOf(SpManager.getInstance().getUserId()), carNumber), new BaseObserver<OnLinePayOrderBean>(mView) {
            @Override
            public void onNext(OnLinePayOrderBean reseverPayOrderBean) {
                mView.hideLoading();
                onLinePayOrder = reseverPayOrderBean;
                mView.showPayConfirm(reseverPayOrderBean);
            }
        });
    }

    @Override
    public void setWeChatPayMode(boolean isChecked) {
        isWechatChecked = isChecked;
        payType = Constants.PAY_TYPE_WECHAT;
    }

    @Override
    public void setAlipayPayMode(boolean isChecked) {
        isAlipayChecked = isChecked;
        payType = Constants.PAY_TYPE_ALIPAY;
    }

    @Override
    public void pay() {
        if (!isWechatChecked && !isAlipayChecked) {
            mView.showNotifySelectPayType();
            return;
        }
        // 2019/7/17  getPayInfo如果PayFlag是3的话表示价格变动 需要再次创建缴费订单 否则调起支付
        //如果需支付为0  与后台确认订单完成 跳转支付成功页面
        if (onLinePayOrder.getParkPrice() == 0) {
            confirmPirceZeroOrder();
        } else {
            mView.showLoading();
            getPayInfo(onLinePayOrder.getOrderCode());
        }
    }

    private void confirmPirceZeroOrder() {
        addSubscribe(dataManager.confirmOrderComplete(onLinePayOrder.getOrderCode(), Constants.PAY_TYPE_ONLINE)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponseV2>(mView) {

                    @Override
                    public void onNext(BaseResponseV2 baseResponseV2) {
                        if (baseResponseV2 != null) {
                            Boolean isSuccess = (Boolean) baseResponseV2.getValue();
                            OrderDetailBean orderDetailBean = new OrderDetailBean();
                            orderDetailBean.setOrderCode(onLinePayOrder.getOrderCode());
                            orderDetailBean.setStartTime(onLinePayOrder.getStartTime());
                            orderDetailBean.setEndTime(onLinePayOrder.getEndTime());
                            orderDetailBean.setShouldPay(onLinePayOrder.getParkPrice());
                            orderDetailBean.setPlateNumber(onLinePayOrder.getPlateNumber());
                            mView.showPayResult(isSuccess, orderDetailBean);
                            if (!isSuccess) mView.showToast(baseResponseV2.getMessage());
                        } else {
                            mView.showNetWorkErrView();
                        }
                    }
                }));
    }

    private void getPayInfo(String orderCode) {
        addSubscribeV2(dataManager.getWechatPayInfo(orderCode, Constants.PAY_TYPE_ONLINE, payType), new BaseObserver<PayInfoBean>(mView) {
            @Override
            public void onNext(PayInfoBean payInfoBean) {
                mView.hideLoading();
                if (payInfoBean.getPayFlag() == 1) {//成功
                    OrderDetailBean orderDetailBean = new OrderDetailBean();
                    orderDetailBean.setOrderCode(onLinePayOrder.getOrderCode());
                    orderDetailBean.setStartTime(onLinePayOrder.getStartTime());
                    orderDetailBean.setEndTime(onLinePayOrder.getEndTime());
                    orderDetailBean.setShouldPay(onLinePayOrder.getParkPrice());
                    orderDetailBean.setPlateNumber(onLinePayOrder.getPlateNumber());
                    if (isWechatChecked) {
//                        PaymentHelper.setGlobalData(orderCode, 1, 0, orderDetailBean);
//                        PaymentHelper.startWeChatPay(activity, payInfoBean.getWxPayUnifiedOrderResult());
                    } else if (isAlipayChecked) {
//                    // TODO: 2019/7/12 调起支付宝支付 支付需要信息在wxPayUnifiedOrderResult中
                        mView.showPayResult(false, orderDetailBean);
                    }
                } else if (payInfoBean.getPayFlag() == 3) {//价格变动
                    mView.showPriceDifferent();
                } else {
                    mView.showNetWorkErrView();
                }

            }
        });
    }

    @Override
    public void setShouldPayCarNumber(String carNumber) {
        this.carNumber = carNumber;
        onLinePayOrder = null;
    }

    @Override
    public int getSelectPosition(String number) {
        int selectPostion = -1;
        for (int i = 0; i < carNumbers.size(); i++) {
            if (carNumbers.get(i).getCarNumber().equals(number)) {
                selectPostion = i;
            }
        }
        return selectPostion;
    }

}