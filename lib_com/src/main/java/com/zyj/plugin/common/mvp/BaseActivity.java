package com.zyj.plugin.common.mvp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.githang.statusbar.StatusBarCompat;
import com.zyj.plugin.common.R;
import com.zyj.plugin.common.event.common.BaseActivityEvent;
import com.zyj.plugin.common.manager.ActivityManager;
import com.zyj.plugin.common.mvp.view.AbstractView;
import com.zyj.plugin.common.uitl.NetUtil;
import com.zyj.plugin.common.uitl.StatusBarUtil;
import com.zyj.plugin.common.view.LoadingInitView;
import com.zyj.plugin.common.view.NetErrorView;
import com.zyj.plugin.common.view.NoDataView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

import static android.view.View.OVER_SCROLL_NEVER;

/**
 * Description: <BaseActivity><br>
 * Author:      gxl<br>
 * Date:        2018/1/16<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseActivity extends AppCompatActivity implements AbstractView {
    protected static final String TAG = BaseActivity.class.getSimpleName();
    protected TextView mTxtTitle;
    protected Toolbar mToolbar;
    protected NetErrorView mNetErrorView;
    protected NoDataView mNoDataView;
    protected LoadingInitView mLoadingInitView;
    private ViewStub mViewStubToolbar;
    private ViewStub mViewStubContent;
    private ViewStub mViewStubInitLoading;
    private ViewStub mViewStubNoData;
    private ViewStub mViewStubError;
    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_root);
        initStatusBar();
        mActivity = this;
        initCommonView();
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        onViewCreated();
        initView();
        initListener();
        initData();
        EventBus.getDefault().register(this);
        ActivityManager.getInstance().addActivity(this);
    }

    public void initStatusBar() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.withe));
    }

    protected void initCommonView() {
        mViewStubToolbar = findViewById(R.id.view_stub_toolbar);
        mViewStubContent = findViewById(R.id.view_stub_content);
        mViewStubContent = findViewById(R.id.view_stub_content);
        mViewStubInitLoading = findViewById(R.id.view_stub_init_loading);
        mViewStubError = findViewById(R.id.view_stub_error);
        mViewStubNoData = findViewById(R.id.view_stub_nodata);

        if (enableToolbar()) {
            mViewStubToolbar.setLayoutResource(onBindToolbarLayout());
            View view = mViewStubToolbar.inflate();
            initToolbar(view);
        }
        mViewStubContent.setLayoutResource(onBindLayout());
        mViewStubContent.inflate();
    }

    protected void initToolbar(View view) {
        mToolbar = view.findViewById(R.id.toolbar_root);
        mTxtTitle = view.findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mTxtTitle != null && !TextUtils.isEmpty(title)) {
            mTxtTitle.setText(title);
        }
        //可以再次覆盖设置title
        String tootBarTitle = getTootBarTitle();
        if (mTxtTitle != null && !TextUtils.isEmpty(tootBarTitle)) {
            mTxtTitle.setText(tootBarTitle);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityManager.getInstance().finishActivity(this);
    }

    public int onBindToolbarLayout() {
        return R.layout.common_toolbar;
    }

    public abstract int onBindLayout();

    public abstract void initView();

    protected void onViewCreated() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    @Override
    public void showToast(int resId) {
        ToastUtils.showShort(resId);
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showErrorMsg(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public String getTootBarTitle() {
        return "";
    }

    public boolean enableToolbar() {
        return false;
    }

    public void showLoading() {
        showInitLoadView(true);
    }

    public void hideLoading() {
        showInitLoadView(false);
    }

    public void showNoDataView() {
        showNoDataView(true);
    }

    public void showNoDataView(int resId) {
        showNoDataView(true, resId);
    }

    public void hideNoDataView() {
        showNoDataView(false);
    }

    public void hideNetWorkErrView() {
        showNetWorkErrView(false);
    }

    public void showNetWorkErrView() {
        showNetWorkErrView(true);
    }

    private void showInitLoadView(boolean show) {
        if (mLoadingInitView == null) {
            View view = mViewStubInitLoading.inflate();
            mLoadingInitView = view.findViewById(R.id.view_init_loading);
        }
        mLoadingInitView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoadingInitView.loading(show);
    }


    private void showNetWorkErrView(boolean show) {
        if (mNetErrorView == null) {
            View view = mViewStubError.inflate();
            mNetErrorView = view.findViewById(R.id.view_net_error);
            mNetErrorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetUtil.checkNetToast()) {
                        return;
                    }
                    hideNetWorkErrView();
                    initData();
                }
            });
        }
        mNetErrorView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    private void showNoDataView(boolean show) {
        if (mNoDataView == null) {
            View view = mViewStubNoData.inflate();
            mNoDataView = view.findViewById(R.id.view_no_data);
        }
        mNoDataView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showNoDataView(boolean show, int resid) {
        showNoDataView(show);
        if (show) {
            mNoDataView.setNoDataView(resid);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public <T> void onEvent(BaseActivityEvent<T> event) {
    }

    @Override
    public Activity getActivityContext() {
        return this;
    }

    public void setTitleBack(String title) {
        TextView textView = findViewById(R.id.tv_title);
        ImageView imageView = findViewById(R.id.iv_left);
        textView.setText(title);
        imageView.setOnClickListener(v -> finish());
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    public void setBack(int resId) {
        ImageView imageView = findViewById(R.id.iv_left);
        imageView.setImageResource(resId);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(v -> finish());
    }

    public RecyclerView initRecyclerView(int resId, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = findViewById(resId);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setOverScrollMode(OVER_SCROLL_NEVER);
        return recyclerView;
    }

    public void setRight(int resId) {
        ImageView imageView = findViewById(R.id.iv_right);
        imageView.setImageResource(resId);
        imageView.setVisibility(View.VISIBLE);
    }
}
