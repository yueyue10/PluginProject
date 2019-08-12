package com.zyj.plugin.login.web;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyj.plugin.login.R;
import com.zyj.plugin.login.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareAdapter extends BaseQuickAdapter<Integer, ShareAdapter.ViewHolder> {

    public ShareAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, Integer item) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SizeUtils.dp2px(50), SizeUtils.dp2px(50));
        helper.imageView.setLayoutParams(layoutParams);
        helper.imageView.setImageResource(item);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R2.id.imageView)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
