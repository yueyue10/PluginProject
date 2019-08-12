package com.zyj.plugin.me.order.detail;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyj.plugin.common.data.bean.KeyValueBean;
import com.zyj.plugin.me.R;
import com.zyj.plugin.me.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailAdapter extends BaseQuickAdapter<KeyValueBean, OrderDetailAdapter.ViewHolder> {

    public OrderDetailAdapter(int layoutResId, @Nullable List<KeyValueBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, KeyValueBean item) {
        helper.keyTv.setText(item.getKey());
        helper.valueTv.setText(item.getValue());
        switch (item.getItem_style()) {
            case 1:
                helper.dividerLine1.setVisibility(View.VISIBLE);
                break;
            case 2:
                helper.dividerLine2.setVisibility(View.VISIBLE);
                break;
        }
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R2.id.key_tv)
        TextView keyTv;
        @BindView(R2.id.value_tv)
        TextView valueTv;
        @BindView(R2.id.divider_line1)
        View dividerLine1;
        @BindView(R2.id.divider_line2)
        View dividerLine2;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
