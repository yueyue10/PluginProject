package com.zyj.plugin.login.login.register;

import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.common.view.CountTimeView;
import com.zyj.plugin.common.view.MyTextWatcher;
import com.zyj.plugin.login.R;
import com.zyj.plugin.login.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.et_password)
    EditText etPassword;
    @BindView(R2.id.iv_password_visible)
    ImageView ivPasswordVisible;
    @BindView(R2.id.et_code)
    EditText etCode;
    @BindView(R2.id.time_code)
    CountTimeView timeCode;
    @BindView(R2.id.tv_register)
    TextView tvRegister;
    @BindView(R2.id.cb_agreement)
    CheckBox cbAgreement;

    @Override
    public int onBindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        setTitle(R.string.register);
        timeCode.setAllowRun(false);
        timeCode.setBackgroundColor(getResources().getColor(R.color.bg_button_orange_30));
        etPhone.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.verificationRegisterable(editable.toString(), etPassword.getText().toString(), etCode.getText().toString(), timeCode.isRun());
            }
        });
        etPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.verificationRegisterable(etPhone.getText().toString(), editable.toString(), etCode.getText().toString(), timeCode.isRun());
            }
        });

        etCode.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.verificationRegisterable(etPhone.getText().toString(), etPassword.getText().toString(), editable.toString(), timeCode.isRun());
            }
        });
        cbAgreement.setOnCheckedChangeListener((buttonView, isChecked) -> mPresenter.changeAgreementChecked(isChecked, etPhone.getText().toString(), etPassword.getText().toString(), etCode.getText().toString()));
    }

    @Override
    public void changeButtonClickStatus(boolean clickable) {
        tvRegister.setClickable(clickable);
        tvRegister.setBackgroundColor(getResources().getColor(clickable ? R.color.bg_button_orange : R.color.bg_button_orange_30));
    }

    @Override
    public void changeimeCodeStatus(boolean clickable) {
        timeCode.setAllowRun(clickable);
        timeCode.setBackgroundColor(getResources().getColor(clickable ? R.color.bg_button_orange : R.color.bg_button_orange_30));
    }

    @Override
    public void showPasswordVisible(boolean visible) {
        etPassword.setInputType(visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ivPasswordVisible.setImageResource(visible ? R.mipmap.show : R.mipmap.close);
        etPassword.setSelection(etPassword.getText().toString().length());
    }

    @Override
    public void showGetCodeSuccess(String resultMessage) {
        showToast(R.string.get_code_success);
        timeCode.setBackgroundResource(R.color.bg_button_orange_30);
        timeCode.startRun();
    }

    @Override
    public void registerSuccess() {
        showToast(R.string.success_register);
        setResult(Constants.RESULT_CODE_LOGIN);
        finish();
    }

    @Override
    public void showGetCodeError() {
        showNotifyDialog(R.string.error_get_code);
    }

    @Override
    public void showGetCodeError(String errorMessage) {
        showNotifyDialog(errorMessage);
    }

    @Override
    public void showPhoneEmptyError() {
        showNotifyDialog(R.string.error_phone_empty);
    }

    @Override
    public void showPhoneNumberError() {
        showNotifyDialog(R.string.error_phone_number);
    }

    @Override
    public void showPhoneFormatError() {
        showNotifyDialog(R.string.error_phone_format);
    }

    @OnClick({R2.id.iv_left, R2.id.tv_register, R2.id.time_code, R2.id.tv_agreement, R2.id.iv_password_visible})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_left) {
            finish();
        } else if (i == R.id.tv_register) {
            mPresenter.register(etPhone.getText().toString(), etPassword.getText().toString(), etCode.getText().toString());
        } else if (i == R.id.time_code) {
            mPresenter.getLoginCode(etPhone.getText().toString());
        } else if (i == R.id.tv_agreement) {
//            startActivity(new Intent(this, WebViewActivity.class)
//                    .putExtra("url", Constants.PATH_AGREENEMT_MLF)
//                    .putExtra("title", R.string.title_agreement_service)
//                    .putExtra("canshare", false));
        } else if (i == R.id.iv_password_visible) {
            mPresenter.changePasswordVisible();
        }
    }
}
