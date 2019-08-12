package com.zyj.plugin.home.payol;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.parkingwang.keyboard.KeyboardInputController;
import com.parkingwang.keyboard.MessageHandler;
import com.parkingwang.keyboard.OnInputChangedListener;
import com.parkingwang.keyboard.PopupKeyboard;
import com.parkingwang.keyboard.view.InputView;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zyj.plugin.common.data.bean.CarNumberInfoBean;
import com.zyj.plugin.common.data.bean.OnLinePayOrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.common.uitl.DialogUtil;
import com.zyj.plugin.home.R;
import com.zyj.plugin.home.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PayOnLineActivity extends BaseMvpActivity<PayOnLinePresenter> implements PayOnLineContract.View {

    @BindView(R2.id.input_view)
    InputView inputView;
    @BindView(R2.id.bt_new_energy)
    Button btNewEnergy;
    @BindView(R2.id.tv_find_car_fail)
    TextView tvFindCarFail;
    @BindView(R2.id.tv_to_pay)
    TextView tvToPay;
    @BindView(R2.id.rl_pay_confirm)
    RelativeLayout rlPayConfirm;
    @BindView(R2.id.tv_pay_on_line_car_number)
    TextView tvPayOnLineCarNumber;
    @BindView(R2.id.tv_pay_price)
    TextView tvPayPrice;
    @BindView(R2.id.tv_pay_on_line_park_name)
    TextView tvPayOnLineParkName;
    @BindView(R2.id.tv_pay_on_line_start_end_time)
    TextView tvPayOnLineStartEndTime;
    @BindView(R2.id.tv_pay_on_line_pay_code)
    TextView tvPayOnLinePayCode;
    @BindView(R2.id.cb_pay_on_line_wechat)
    CheckBox cbPayOnLineWechat;
    @BindView(R2.id.cb_pay_on_line_alipay)
    CheckBox cbPayOnLineAlipay;
    @BindView(R2.id.tv_pay_on_line_pay)
    TextView tvPayOnLinePay;
    @BindView(R2.id.tv_pay_on_line_cost)
    TextView tvPayOnLineCost;
    @BindView(R2.id.ll_pay_type)
    LinearLayout llPayType;
    @BindView(R2.id.nsv)
    NestedScrollView nsv;
    @BindView(R2.id.ll_find_car)
    LinearLayout llFindCar;
    private CarNumberAdapter adapter;
    private PopupKeyboard mPopupKeyboard;

    @Override
    public int onBindLayout() {
        return R.layout.activity_pay_on_line;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.title_pay_online));
        initBindCarNumberList();
        initInputView();
        showSaveUnClickAble();
        initPayConfirm();
    }

    private void initPayConfirm() {
        cbPayOnLineWechat.setChecked(true);
        cbPayOnLineWechat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mPresenter.setWeChatPayMode(isChecked);
            if (isChecked && cbPayOnLineAlipay.isChecked()) {
                cbPayOnLineAlipay.setChecked(false);
            }
        });
        cbPayOnLineAlipay.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mPresenter.setAlipayPayMode(isChecked);
            if (isChecked && cbPayOnLineWechat.isChecked()) {
                cbPayOnLineWechat.setChecked(false);
            }
        });
    }

    private void initInputView() {
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(inputView, this);
        // 隐藏确定按钮
        mPopupKeyboard.getKeyboardEngine().setHideOKKey(true);
        mPopupKeyboard.getController()
                .setDebugEnabled(false)
                .setSwitchVerify(false)
                .setMessageHandler(new MessageHandler() {
                    @Override
                    public void onMessageError(int message) {
                    }

                    @Override
                    public void onMessageTip(int message) {
                    }
                })
                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(btNewEnergy) {
                    @Override
                    public void onNumberTypeChanged(boolean isNewEnergyType) {
                        if (isNewEnergyType) {
                            btNewEnergy.setTextColor(getResources().getColor(R.color.line_gray));
                            btNewEnergy.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_stroke_gray));
                        } else {
                            btNewEnergy.setTextColor(getResources().getColor(R.color.bg_button_orange));
                            btNewEnergy.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_stroke));
                        }
                    }
                });
        mPopupKeyboard.getController().addOnInputChangedListener(new OnInputChangedListener() {
            @Override
            public void onChanged(String number, boolean isCompleted) {
                if (isCompleted) {
                    mPopupKeyboard.dismiss(PayOnLineActivity.this);
                    showSaveClickAble();
                    adapter.setSelectPosition(mPresenter.getSelectPosition(number));
                    adapter.notifyDataSetChanged();
                    mPresenter.setShouldPayCarNumber(number);
                } else {
                    showSaveUnClickAble();
                    adapter.setSelectPosition(-1);
                    adapter.notifyDataSetChanged();
                }

                tvFindCarFail.setVisibility(View.GONE);
            }

            @Override
            public void onCompleted(String number, boolean isAutoCompleted) {
                mPopupKeyboard.dismiss(PayOnLineActivity.this);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.initData();
    }

    private void initBindCarNumberList() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        adapter = new CarNumberAdapter(R.layout.item_car_number, new ArrayList<>());
        RecyclerView recyclerView = initRecyclerView(R.id.rv_bind_car, adapter, gridLayoutManager);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3, DensityUtil.dp2px(10), false);
        recyclerView.addItemDecoration(itemDecoration);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            String carNumber = ((CarNumberInfoBean) adapter.getItem(position)).getCarNumber();
            mPopupKeyboard.getController().updateNumber(carNumber);
            if (carNumber.length() == 7) {
                btNewEnergy.setTextColor(getResources().getColor(R.color.bg_button_orange));
                btNewEnergy.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_stroke));
            } else if (carNumber.length() == 8) {
                btNewEnergy.setTextColor(getResources().getColor(R.color.line_gray));
                btNewEnergy.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_stroke_gray));
            }
            mPopupKeyboard.dismiss(PayOnLineActivity.this);
            showSaveClickAble();
            this.adapter.setSelectPosition(position);
            adapter.notifyDataSetChanged();
            mPresenter.setShouldPayCarNumber(carNumber);
            tvFindCarFail.setVisibility(View.GONE);
        });
    }

    @OnClick({R2.id.iv_left, R2.id.tv_to_pay, R2.id.tv_pay_on_line_pay, R2.id.view_transparent})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_left) {
            finish();
        } else if (i == R.id.tv_to_pay) {
            mPresenter.toPay();
        } else if (i == R.id.tv_pay_on_line_pay) {
            mPresenter.pay();
        } else if (i == R.id.view_transparent) {
            rlPayConfirm.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSaveClickAble() {
        tvToPay.setClickable(true);
        tvToPay.setBackgroundColor(getResources().getColor(R.color.bg_button_orange));
    }

    @Override
    public void showSaveUnClickAble() {
        tvToPay.setClickable(false);
        tvToPay.setBackgroundColor(getResources().getColor(R.color.bg_button_orange_30));
    }

    @Override
    public void showCarNumbers(List<CarNumberInfoBean> carNumberInfoBeans) {
        adapter.setNewData(carNumberInfoBeans);
    }

    @Override
    public void showFindFail() {
//        tvFindCarFail.setVisibility(View.VISIBLE);
//        tvFindCarFail.setText(String.format("%s没在地下停车场", inputView.getNumber()));
        nsv.requestChildFocus(llFindCar, tvFindCarFail);
        showToast(String.format("%s没在地下停车场", inputView.getNumber()));
    }


    @Override
    public void showPayConfirm(OnLinePayOrderBean onLinePayOrderBean) {
        rlPayConfirm.setVisibility(View.VISIBLE);
        tvPayPrice.setText(String.format("￥%s", onLinePayOrderBean.getParkPrice()));
        tvPayOnLineParkName.setText(onLinePayOrderBean.getParkName());
        tvPayOnLineCarNumber.setText(onLinePayOrderBean.getPlateNumber());
        tvPayOnLineStartEndTime.setText(String.format("%s / %s", onLinePayOrderBean.getStartTime(), onLinePayOrderBean.getEndTime()));
        tvPayOnLinePayCode.setText(onLinePayOrderBean.getOrderCode());
        tvPayOnLinePay.setText(String.format("去支付  ￥%s", onLinePayOrderBean.getParkPrice()));

        llPayType.setVisibility(onLinePayOrderBean.getParkPrice() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showNotifySelectPayType() {
        DialogUtil.showNotifyDialog(this, "请选择支付方式。");
    }

    @Override
    public void showPayResult(boolean isPaySuccess, OrderDetailBean orderDetailBean) {

    }

    @Override
    public void showWxPayResult(boolean isPaySuccess) {
        if (isPaySuccess) finish();
    }

    @Override
    public void showPriceDifferent() {
        DialogUtil.showNotifyDialog(this, "重要提示", "您的订单金额有变化，为了确保您的权益，请确认金额后重新支付。", (dialog, which) -> mPresenter.createOrder(true));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mPopupKeyboard != null && mPopupKeyboard.isShown()) {
                mPopupKeyboard.dismiss(this);
            } else if (rlPayConfirm.getVisibility() == View.VISIBLE) {
                rlPayConfirm.setVisibility(View.GONE);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
