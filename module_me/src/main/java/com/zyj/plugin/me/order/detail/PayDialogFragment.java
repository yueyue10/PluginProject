package com.zyj.plugin.me.order.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.mvp.dialog.BaseNorDialogFragment;
import com.zyj.plugin.common.uitl.ShapeUtils;
import com.zyj.plugin.me.R;
import com.zyj.plugin.me.R2;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class PayDialogFragment extends BaseNorDialogFragment {

    @BindView(R2.id.go_pay_tv)
    TextView go_pay_tv;
    @BindView(R2.id.wechat_pay_check_iv)
    ImageView wechat_pay_check_iv;
    @BindView(R2.id.alipay_check_iv)
    ImageView alipay_check_iv;
    String payType = Constants.PAY_TYPE_WECHAT;// payType 1 支付宝  2 微信
    double totalPrice;

    public static PayDialogFragment getInstance(double totalPrice) {
        PayDialogFragment fragment = new PayDialogFragment();
        Bundle args = new Bundle();
        args.putDouble("totalPrice", totalPrice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_pay_dialog;
    }

    @Override
    public void initView(View view) {
        checkView(0);
        ShapeUtils.setShapeCorner2ColorStr(go_pay_tv, "#F3A230", 2);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R2.id.wechat_layout, R2.id.alipay_layout, R2.id.go_pay_tv})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.wechat_layout) {
            checkView(0);
        } else if (i == R.id.alipay_layout) {
            checkView(1);
        } else if (i == R.id.go_pay_tv) {
            goToPayClickListener.onGoToPay(payType);
            dismiss();
        }
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        totalPrice = bundle.getDouble("totalPrice");
        go_pay_tv.setText(String.format("去支付 ¥%s", String.valueOf(totalPrice)));
    }

    private void checkView(int check) {
        switch (check) {
            case 0:
                payType = Constants.PAY_TYPE_WECHAT;
                wechat_pay_check_iv.setImageResource(R.mipmap.single_selection_x);
                alipay_check_iv.setImageResource(R.mipmap.single_selection_m);
                break;
            case 1:
                payType = Constants.PAY_TYPE_ALIPAY;
                alipay_check_iv.setImageResource(R.mipmap.single_selection_x);
                wechat_pay_check_iv.setImageResource(R.mipmap.single_selection_m);
                break;
        }
    }

    public GoToPayClickListener goToPayClickListener;

    public interface GoToPayClickListener {
        void onGoToPay(String payType);
    }

    public void setGoToPayListener(GoToPayClickListener goToPayClickListener) {
        this.goToPayClickListener = goToPayClickListener;
    }
}
