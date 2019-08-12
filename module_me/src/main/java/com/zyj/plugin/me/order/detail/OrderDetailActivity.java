package com.zyj.plugin.me.order.detail;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.zyj.plugin.common.data.bean.KeyValueBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.data.utils.RxBus;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.common.uitl.DialogUtil;
import com.zyj.plugin.common.uitl.ShapeUtils;
import com.zyj.plugin.me.R;
import com.zyj.plugin.me.R2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseMvpActivity<OrderDetailPresenter> implements OrderDetailContract.View {

    @BindView(R2.id.top_layout)
    ConstraintLayout top_layout;
    @BindView(R2.id.order_status_tv)
    TextView order_status_tv;
    @BindView(R2.id.charge_standard_tv)
    TextView charge_standard_tv;
    @BindView(R2.id.price_tv)
    TextView price_tv;
    OrderDetailAdapter orderDetailAdapter;
    List<KeyValueBean> keyValueBeans;
    OrderDetailBean mOrderDetailBean;
    FooterView footer;
    String orderCode;

    @Override
    public int onBindLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        setTitleBack(getString(R.string.order_detail_title));
        initRecyclerView();
    }

    private void initRecyclerView() {
        keyValueBeans = new ArrayList<>();
        orderDetailAdapter = new OrderDetailAdapter(R.layout.item_order_detail, keyValueBeans);
        orderDetailAdapter.addFooterView(getFooterView());
        initRecyclerView(R.id.order_detail_rv, orderDetailAdapter, new LinearLayoutManager(mActivity));
    }

    @Override
    public void initData() {
        if (getIntent().getStringExtra("orderCode") != null)
            orderCode = getIntent().getStringExtra("orderCode");
        mPresenter.getOrderDetail(orderCode);
    }

    @SuppressLint("InflateParams")
    private View getFooterView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_order_detail_footer, null);
        footer = new FooterView(view);
        return view;
    }

    @Override
    public void getOrderDetailSuccess(OrderDetailBean orderDetailBean) {
        this.mOrderDetailBean = orderDetailBean;
        keyValueBeans.clear();
        keyValueBeans.addAll(mPresenter.getKeyValueData(orderDetailBean,
                orderDetailBean.getPlateNumber(),
                orderDetailBean.getParkName(),
                orderDetailBean.getStartTime(),
                orderDetailBean.getEndTime(),
                orderDetailBean.getOrderCode(),
                orderDetailBean.getCreateTime()));
        orderDetailAdapter.notifyDataSetChanged();
        //头布局赋值
        order_status_tv.setText(orderDetailBean.getOrderStateStr());
        long time = TimeUtils.getTimeSpan(orderDetailBean.getEndTime(), orderDetailBean.getStartTime(), TimeConstants.HOUR);
        String priceStr = String.format("%.0f小时x%.0f元/小时", (float) time, (float) orderDetailBean.getParkPrice());
        charge_standard_tv.setText(priceStr);
        price_tv.setText("¥" + new DecimalFormat("#").format(orderDetailBean.getShouldPay()));
        //底布局赋值
        footer.orderPriceTv1.setText("¥ " + new DecimalFormat("#").format(orderDetailBean.getShouldPay()));
        if (orderDetailBean.getDiscountPrice() != 0) {
            footer.discountPriceTv1.setText("-¥ " + orderDetailBean.getDiscountPriceStr());
            footer.discountPriceTv1.setVisibility(View.VISIBLE);
            footer.discount_price_tv.setVisibility(View.VISIBLE);
        } else {
            footer.discountPriceTv1.setVisibility(View.GONE);
            footer.discount_price_tv.setVisibility(View.GONE);
        }
        footer.realPayPriceTv1.setText("¥ " + orderDetailBean.getTotalStr());
        //
        if (orderDetailBean.getOrderState() != 0) {
            top_layout.setVisibility(View.GONE);
            footer.hideBottomLayout();
        }
    }

    @Override
    public void getPayProcessSuccess() {
        showToast("支付成功");
        mPresenter.getOrderDetail(orderCode);
    }

    @Override
    public void showPayResult(Boolean isPaySuccess, OrderDetailBean orderDetail) {

    }

    @Override
    public void showPriceDifferent() {
        DialogUtil.showNotifyDialog(this,
                "重要提示",
                "您的订单金额有变化，为了确保您的权益，请确认金额后重新选择支付方式。",
                (dialog, which) -> initData());
    }

    @Override
    public void cancelOrderSuccess() {
        mPresenter.getOrderDetail(orderCode);
        RxBus.getDefault().post(new OrderDetailBean());
    }

    public class FooterView {

        @BindView(R2.id.pay_layout)
        RelativeLayout pay_layout;
        @BindView(R2.id.pay_tv)
        TextView pay_tv;
        @BindView(R2.id.cancel_order_tv)
        TextView cancel_order_tv;
        @BindView(R2.id.order_price_tv1)
        TextView orderPriceTv1;
        @BindView(R2.id.discount_price_tv1)
        TextView discountPriceTv1;
        @BindView(R2.id.discount_price_tv)
        TextView discount_price_tv;
        @BindView(R2.id.real_pay_price_tv1)
        TextView realPayPriceTv1;
        @BindView(R2.id.divider_line3)
        View divider_line3;

        @OnClick({R2.id.cancel_order_tv, R2.id.pay_tv})
        public void onViewClicked(View view) {
            int i = view.getId();
            if (i == R.id.cancel_order_tv) {
                DialogUtil.showDialog(mActivity,
                        "提示",
                        "确定要取消订单吗？",
                        "确定",
                        "取消",
                        (dialog, which) -> mPresenter.cancelOrder(orderCode),
                        (dialog, which) -> dialog.dismiss());
            } else if (i == R.id.pay_tv) {
                if (mOrderDetailBean == null)
                    return;
                if (mOrderDetailBean.getTotal() < 0)
                    return;
                if (mOrderDetailBean.getTotal() == 0) {
                    mPresenter.confirmPirceZeroOrder(mOrderDetailBean);
                } else {
//                        if (payDialogFragment == null) {
//                            payDialogFragment = PayDialogFragment.getInstance(mOrderDetailBean.getTotal());
//                        }
//                        payDialogFragment.setGoToPayListener(payType -> mPresenter.getPayProcess(mOrderDetailBean, payType));
//                        payDialogFragment.show(getSupportFragmentManager(), "ShareFragment");
                }
            }
        }

        public FooterView(View view) {
            ButterKnife.bind(this, view);
            ShapeUtils.setShapeCorner2ColorStr(pay_tv, "#F3A230", 2);
            ShapeUtils.setShapeCorner2Color2StrokeStr(cancel_order_tv, "#FFFFFF", 0, "#999999", 1);
        }

        public void hideBottomLayout() {
            cancel_order_tv.setVisibility(View.GONE);
            pay_layout.setVisibility(View.GONE);
            divider_line3.setVisibility(View.VISIBLE);
        }
    }
}
