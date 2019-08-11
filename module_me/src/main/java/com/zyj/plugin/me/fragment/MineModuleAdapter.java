package com.zyj.plugin.me.fragment;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyj.plugin.common.data.bean.ResourceBean;
import com.zyj.plugin.me.R;
import com.zyj.plugin.me.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineModuleAdapter extends BaseQuickAdapter<ResourceBean, MineModuleAdapter.ViewHolder> {

    public MineModuleAdapter(int layoutResId, @Nullable List<ResourceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, ResourceBean item) {
        helper.moduleTv.setText(item.getName());
        helper.moduleIv.setImageResource(item.getResId());
        helper.order_status_tv.setText(item.getOrderStatus());
        switch (item.getStyle()) {
            case 1:
                helper.divider_line2.setVisibility(View.GONE);
                break;
            case 2:
                helper.divider_line2.setVisibility(View.VISIBLE);
                break;
        }
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R2.id.module_iv)
        ImageView moduleIv;
        @BindView(R2.id.module_tv)
        TextView moduleTv;
        @BindView(R2.id.order_status_tv)
        TextView order_status_tv;
        @BindView(R2.id.divider_line1)
        View divider_line1;
        @BindView(R2.id.divider_line2)
        View divider_line2;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
