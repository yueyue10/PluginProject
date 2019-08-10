package com.zyj.plugin.fragment;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyj.plugin.R;
import com.zyj.plugin.common.data.bean.ResourceBean;

import java.util.List;

public class HomeModuleAdapter extends BaseQuickAdapter<ResourceBean, HomeModuleAdapter.ViewHolder> {

    public HomeModuleAdapter(int layoutResId, @Nullable List<ResourceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, ResourceBean item) {
        helper.moduleTv.setText(item.getName());
        helper.moduleIv.setImageResource(item.getResId());
    }

    class ViewHolder extends BaseViewHolder {
        ImageView moduleIv;
        TextView moduleTv;

        public ViewHolder(View view) {
            super(view);
            moduleIv=view.findViewById(R.id.module_iv);
            moduleTv=view.findViewById(R.id.module_tv);
        }
    }
}
