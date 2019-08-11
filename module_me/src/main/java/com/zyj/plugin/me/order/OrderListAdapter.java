package com.zyj.plugin.me.order;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ColorUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyj.plugin.common.data.bean.OrderBean;
import com.zyj.plugin.common.uitl.ConverterUtils;
import com.zyj.plugin.common.uitl.ShapeUtils;
import com.zyj.plugin.me.R;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置列表适配器
 *
 * @author quchao
 * @date 2018/2/24
 */
public class OrderListAdapter extends BaseQuickAdapter<OrderBean, OrderListAdapter.AtlasViewHolder> {


    public OrderListAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(AtlasViewHolder helper, OrderBean item) {
        //0 待支付 1预约完成 2 已取消 3 订单关闭 4 已完成
        switch (item.getOrderState()) {
            case 0:
                helper.cancelOrderTv.setVisibility(View.VISIBLE);
                helper.payNowTv.setVisibility(View.VISIBLE);
                helper.orderStatusTv.setTextColor(ColorUtils.string2Int("#EBA542"));
                break;
            default:
                helper.cancelOrderTv.setVisibility(View.GONE);
                helper.payNowTv.setVisibility(View.GONE);
                helper.orderStatusTv.setTextColor(ColorUtils.string2Int("#1B1717"));
                break;
        }
        ShapeUtils.setShapeCorner2Color2StrokeStr(helper.cancelOrderTv, "#FFFFFF", 0, "#999999", 1);
        helper.orderTimeTv.setText("下单时间：" + item.getCreateTime());
        helper.orderStatusTv.setText(item.getOrderStateStr());
        helper.parkLocationTv.setText(ConverterUtils.convertCarNumStyle(item.getPlateNumber()) + "  " + item.getParkName());
        helper.parkTimeTv.setText(String.format("%s —— %s", item.getStartTime(), item.getEndTime()));
        helper.orderPriceTv.setText("实付金额：￥" + new DecimalFormat("#").format(item.getTotalPrice()));
        helper.addOnClickListener(R.id.cancel_order_tv);
        helper.addOnClickListener(R.id.pay_now_tv);
    }

    class AtlasViewHolder extends BaseViewHolder {

        @BindView(R.id.order_time_tv)
        TextView orderTimeTv;
        @BindView(R.id.order_status_tv)
        TextView orderStatusTv;
        @BindView(R.id.order_car_iv)
        ImageView orderCarIv;
        @BindView(R.id.park_location_tv)
        TextView parkLocationTv;
        @BindView(R.id.park_time_tv)
        TextView parkTimeTv;
        @BindView(R.id.order_price_tv)
        TextView orderPriceTv;
        @BindView(R.id.cancel_order_tv)
        TextView cancelOrderTv;
        @BindView(R.id.pay_now_tv)
        TextView payNowTv;

        public AtlasViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
