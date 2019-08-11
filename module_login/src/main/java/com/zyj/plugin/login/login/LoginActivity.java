package com.zyj.plugin.login.login;


import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.mvp.BaseMvpActivity;
import com.zyj.plugin.common.view.CountTimeView;
import com.zyj.plugin.common.view.MyTextWatcher;
import com.zyj.plugin.login.R;
import com.zyj.plugin.login.R2;
import com.zyj.plugin.login.login.forgetpassword.ForgetPasswordActivity;
import com.zyj.plugin.login.login.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R2.id.iv_left)
    ImageView ivLeft;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_right)
    TextView tvRight;
    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.et_code)
    EditText etCode;
    @BindView(R2.id.time_code)
    CountTimeView timeCode;
    @BindView(R2.id.iv_password_visible)
    ImageView ivPasswordVisible;
    @BindView(R2.id.tv_login)
    TextView tvLogin;
    @BindView(R2.id.tv_change_login_type)
    TextView tvChangeLoginType;
    @BindView(R2.id.tv_forget_password)
    TextView tvForgetPassword;

    @Override
    public int onBindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        initTitle();
        initCode();
    }

    private void initCode() {
        timeCode.setAllowRun(false);
        timeCode.setTimeColor(getResources().getColor(R2.color.bg_button_orange));
        timeCode.setBackgroundColor(getResources().getColor(R2.color.bg_button_orange_30));
        etPhone.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.verificationPhoneAndCode(editable.toString(), etCode.getEditableText().toString(), timeCode.isRun());
            }
        });
        etCode.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.verificationPhoneAndCode(etPhone.getEditableText().toString(), editable.toString(), timeCode.isRun());
            }
        });
        ivPasswordVisible.setOnClickListener(v -> mPresenter.changePasswordVisible());
    }

    private void initTitle() {
        ivLeft.setVisibility(View.VISIBLE);
        tvTitle.setText(getString(R2.string.login));
        tvRight.setText(getString(R2.string.register));
        tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeButtonClickStatus(boolean clickable) {
        tvLogin.setClickable(clickable);
        tvLogin.setBackgroundColor(getResources().getColor(clickable ? R2.color.bg_button_orange : R2.color.bg_button_orange_30));
    }

    @Override
    public void changeimeCodeStatus(boolean clickable) {
        timeCode.setAllowRun(clickable);
        timeCode.setBackgroundColor(getResources().getColor(clickable ? R2.color.bg_button_orange : R2.color.bg_button_orange_30));
    }


    @Override
    public void showCodeLoginView() {
        timeCode.setVisibility(View.VISIBLE);
        ivPasswordVisible.setVisibility(View.GONE);
        tvChangeLoginType.setText(R2.string.text_change_login_password);
        tvForgetPassword.setVisibility(View.GONE);
        etCode.setHint(R2.string.et_hint_code);
        etCode.setText("");
        etCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        etCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
    }

    @Override
    public void showPasswordLoginView() {
        timeCode.setVisibility(View.GONE);
        ivPasswordVisible.setVisibility(View.VISIBLE);
        tvForgetPassword.setVisibility(View.VISIBLE);
        tvChangeLoginType.setText(R2.string.text_change_login_code);
        etCode.setHint(getString(R2.string.et_hint_input_password));
        etCode.setText("");
        showPasswordVisible(false);
        etCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
    }

    @Override
    public void showPasswordVisible(boolean visible) {
        etCode.setInputType(visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ivPasswordVisible.setImageResource(visible ? R.mipmap.show : R.mipmap.close);
        etCode.setSelection(etCode.getText().toString().length());
    }

    @Override
    public void showGetCodeSuccess(String resultMessage) {
        showToast(R2.string.get_code_success);
        timeCode.setBackgroundResource(R2.color.bg_button_orange_30);
        timeCode.startRun();
    }

    @Override
    public void loginSuccess() {
        setResult(Constants.RESULT_CODE_LOGIN);
        finish();
    }

    @Override
    public void showGetCodeError() {
        showNotifyDialog(R2.string.error_get_code);
    }

    @Override
    public void showGetCodeError(String errorMessage) {
        showNotifyDialog(errorMessage);
    }

    @Override
    public void showPhoneEmptyError() {
        showNotifyDialog(R2.string.error_phone_empty);
    }

    @Override
    public void showPhoneNumberError() {
        showNotifyDialog(R2.string.error_phone_number);
    }

    @Override
    public void showPhoneFormatError() {
        showNotifyDialog(R2.string.error_phone_format);
    }

    @OnClick({R2.id.iv_left, R2.id.tv_right, R2.id.time_code, R2.id.iv_password_visible, R2.id.tv_login, R2.id.tv_change_login_type, R2.id.tv_forget_password})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_left) {
            finish();
        } else if (i == R.id.tv_right) {
            startActivityForResult(new Intent(this, RegisterActivity.class), Constants.REQUEST_CODE_LOGIN);
        } else if (i == R.id.time_code) {
            mPresenter.getLoginCode(etPhone.getText().toString());
        } else if (i == R.id.iv_password_visible) {
            mPresenter.changePasswordVisible();
        } else if (i == R.id.tv_login) {
            mPresenter.login(etPhone.getText().toString(), etCode.getText().toString());
        } else if (i == R.id.tv_change_login_type) {
            mPresenter.checkLoginType();
        } else if (i == R.id.tv_forget_password) {
            startActivityForResult(new Intent(this, ForgetPasswordActivity.class), Constants.REQUEST_CODE_LOGIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_LOGIN && resultCode == Constants.RESULT_CODE_LOGIN) {
            setResult(Constants.RESULT_CODE_LOGIN);
            finish();
        }
    }
}
