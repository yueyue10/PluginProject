package com.zyj.plugin.home.payol;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyj.plugin.common.data.bean.CarNumberInfoBean;
import com.zyj.plugin.home.R;
import com.zyj.plugin.home.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarNumberAdapter extends BaseQuickAdapter<CarNumberInfoBean, CarNumberAdapter.ViewHolder> {

    public int position = -1;

    public void setSelectPosition(int position) {
        this.position = position;
    }

    public CarNumberAdapter(int layoutResId, @Nullable List<CarNumberInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, CarNumberInfoBean carNumber) {
        if (position == helper.getAdapterPosition()) {
            helper.itemView.setBackgroundResource(R.drawable.bg_stroke_orange_corner);
            helper.tvCarNumber.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            helper.itemView.setBackgroundResource(R.drawable.bg_gray_corner_16dp);
            helper.tvCarNumber.setTextColor(mContext.getResources().getColor(R.color.tv_login_change));
        }
        helper.tvCarNumber.setText(carNumber.getCarNumber());
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R2.id.tv_car_number)
        TextView tvCarNumber;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}