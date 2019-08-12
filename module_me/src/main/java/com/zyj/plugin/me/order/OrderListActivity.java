package com.zyj.plugin.me.order;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ColorUtils;
import com.zyj.plugin.common.data.bean.OrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.mvp.BaseRefreshActivity;
import com.zyj.plugin.common.uitl.DialogUtil;
import com.zyj.plugin.common.uitl.recyclerv.HorizontalDividerItemDecoration;
import com.zyj.plugin.me.R;
import com.zyj.plugin.me.R2;
import com.zyj.plugin.me.order.detail.PayDialogFragment;
import com.zyj.plugin.me.utils.JudgeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends BaseRefreshActivity<OrderListPresenter> implements OrderListContract.View {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    OrderListAdapter orderListAdapter;
    ArrayList<OrderBean> orderBeans;
    PayDialogFragment payDialogFragment;

    @Override
    public int onBindLayout() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.order_list_title));
        configRefreshLayout(true, true, true);
        recyclerView.setBackgroundColor(ColorUtils.string2Int("#F5F5F5"));
    }

    @Override
    protected void clearRefreshData() {
        clearData(orderBeans);
    }

    @Override
    public void initRecyclerView() {
        orderBeans = new ArrayList<>();
        orderListAdapter = new OrderListAdapter(R.layout.item_order_list, orderBeans);
        recyclerView.setAdapter(orderListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mActivity)
                .colorResId(R.color.bg_gray)
                .sizeResId(R.dimen.dp_10).build());
    }

    @Override
    public void initListener() {
        orderListAdapter.setOnItemClickListener((adapter, view, position) ->
                JudgeUtils.startOrderDetailAc(mActivity, orderBeans.get(position).getOrderCode()));
        orderListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int i = view.getId();//取消订单
            if (i == R.id.cancel_order_tv) {
                DialogUtil.showDialog(mActivity,
                        "提示",
                        "确定要取消订单吗？"
                        , "确定"
                        , "取消",
                        (dialog, which) -> mPresenter.cancelOrder(orderBeans.get(position).getOrderCode()),
                        (dialog, which) -> dialog.dismiss());
            } else if (i == R.id.pay_now_tv) {
                if (orderBeans.get(position).getTotalPrice() < 0)
                    return;
                if (orderBeans.get(position).getTotalPrice() == 0) {
                    mPresenter.confirmPirceZeroOrder(orderBeans.get(position));
                } else {
                    if (payDialogFragment == null) {
                        payDialogFragment = PayDialogFragment.getInstance(orderBeans.get(position).getTotalPrice());
                    }
                    payDialogFragment.setGoToPayListener(payType -> {
                        mPresenter.getPayProcess(orderBeans.get(position), payType);
                    });
                    payDialogFragment.show(getSupportFragmentManager(), "ShareFragment");
                }
            }
        });
    }

    @Override
    public void getOrderListSuccess(List<OrderBean> orderBeans) {
        this.orderBeans.addAll(orderBeans);
        if (orderListAdapter.getEmptyView() == null) {
            orderListAdapter.bindToRecyclerView(recyclerView);
            orderListAdapter.setEmptyView(R.layout.empty_order_layout);
        }
        orderListAdapter.notifyDataSetChanged();
        stopRefreshView();
    }

    @Override
    public void cancelOrderSuccess() {
        autoLoadData();
    }

    @Override
    public void getPayProcessSuccess() {
        autoLoadData();
    }

    @Override
    public void showPayResult(Boolean isPaySuccess, OrderDetailBean orderDetailBean) {

    }

    @Override
    public void showPriceDifferent() {
        DialogUtil.showNotifyDialog(this, "重要提示", "您的订单金额有变化，为了确保您的权益，请确认金额后重新支付。", (dialog, which) -> autoLoadData());
    }

    @Override
    public void showPaySuccess() {
        showToast(R.string.pay_success);
        autoLoadData();
    }

    @Override
    public void showPayFail() {
        showToast(R.string.pay_fail);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getBooleanExtra("isSuccess", false)) {
            autoLoadData();
        }
    }
}
