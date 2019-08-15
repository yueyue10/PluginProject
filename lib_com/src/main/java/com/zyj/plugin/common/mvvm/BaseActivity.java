package com.zyj.plugin.common.mvvm;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.githang.statusbar.StatusBarCompat;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zyj.plugin.common.R;
import com.zyj.plugin.common.event.common.BaseActivityEvent;
import com.zyj.plugin.common.manager.ActivityManager;
import com.zyj.plugin.common.mvvm.view.IBaseView;
import com.zyj.plugin.common.uitl.NetUtil;
import com.zyj.plugin.common.view.LoadingInitView;
import com.zyj.plugin.common.view.NetErrorView;
import com.zyj.plugin.common.view.NoDataView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Description: <BaseActivity><br>
 * Author:      mxdl<br>
 * Date:        2019/06/30<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    protected static final String TAG = BaseActivity.class.getSimpleName();
    protected RelativeLayout titleLayout;
    protected TextView mTxtTitle;
    protected Toolbar mToolbar;
    protected ImageView backIv;
    protected TextView rightTv;
    protected ImageView rightIv;
    protected NetErrorView mNetErrorView;
    protected NoDataView mNoDataView;
    protected LoadingInitView mLoadingInitView;
    private ViewStub mViewStubToolbar;
    private ViewStub mViewStubContent;
    private ViewStub mViewStubInitLoading;
    private ViewStub mViewStubNoData;
    private ViewStub mViewStubError;
    protected View mContentView;
    private boolean enableTitleBar = false;
    private boolean enableStatusBar = false;
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_root);
        mActivity = this;
        initCommonView();
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        initView();
        initRecyclerView();
        initListener();
        initParam();
        initData();
        EventBus.getDefault().register(this);
        ActivityManager.getInstance().addActivity(this);
    }


    protected void initCommonView() {
        mViewStubToolbar = findViewById(R.id.view_stub_toolbar);
        mViewStubContent = findViewById(R.id.view_stub_content);
        mViewStubInitLoading = findViewById(R.id.view_stub_init_loading);
        mViewStubError = findViewById(R.id.view_stub_error);
        mViewStubNoData = findViewById(R.id.view_stub_nodata);
        enableTitleStatusBar();
        if (enableToolbar()) {
            mViewStubToolbar.setLayoutResource(onBindToolbarLayout());
            View view = mViewStubToolbar.inflate();
            initToolbar(view);
        }
        if (enableTitleBar) {
            mViewStubToolbar.setLayoutResource(onBindTitleLayout());
            View view = mViewStubToolbar.inflate();
            initTitleBar(view);
        }
        if (enableStatusBar)
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.withe));
        mViewStubContent.setLayoutResource(onBindLayout());
        mContentView = mViewStubContent.inflate();
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

    private void initTitleBar(View view) {
        titleLayout = view.findViewById(R.id.ll_title_content);
        mTxtTitle = view.findViewById(R.id.tv_title);
        backIv = view.findViewById(R.id.iv_left);
        rightIv = findViewById(R.id.iv_right);
        rightTv = findViewById(R.id.tv_right);
        backIv.setOnClickListener(v -> finish());
        mTxtTitle.setVisibility(View.VISIBLE);
        backIv.setVisibility(View.VISIBLE);
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

    public int onBindTitleLayout() {
        return R.layout.layout_title;
    }

    public boolean enableToolbar() {
        return false;
    }

    public void enableTitleStatusBar() {
        enableTitleStatusBar(true, true);
    }

    @Override
    public void enableTitleStatusBar(boolean title, boolean status) {
        enableTitleBar = title;
        enableStatusBar = status;
    }

    public abstract int onBindLayout();

    public abstract void initView();

    public abstract void initData();

    public void initListener() {
    }

    public void initParam() {
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public String getTootBarTitle() {
        return "";
    }

    public void showInitLoadView(boolean show) {
        if (mLoadingInitView == null) {
            View view = mViewStubInitLoading.inflate();
            mLoadingInitView = view.findViewById(R.id.view_init_loading);
        }
        mLoadingInitView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoadingInitView.loading(show);
    }


    public void showNetWorkErrView(boolean show) {
        if (mNetErrorView == null) {
            View view = mViewStubError.inflate();
            mNetErrorView = view.findViewById(R.id.view_net_error);
            mNetErrorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetUtil.checkNetToast()) {
                        return;
                    }
                    showNetWorkErrView(false);
                    initData();
                }
            });
        }
        mNetErrorView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    public void showNoDataView(boolean show) {
        if (mNoDataView == null) {
            View view = mViewStubNoData.inflate();
            mNoDataView = view.findViewById(R.id.view_no_data);
        }
        mNoDataView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public <T> void onEvent(BaseActivityEvent<T> event) {
    }

    @Override
    public Activity getActivityContext() {
        return this;
    }

    @Override
    public void initRecyclerView() {

    }

    @Override
    public RecyclerView initRecyclerView(int resId, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = findViewById(resId);
        return initRecyclerView(recyclerView, adapter, layoutManager);
    }

    @Override
    public RecyclerView initRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        return initRecyclerView(recyclerView, adapter, layoutManager, null);
    }

    @Override
    public RecyclerView initRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager, RecyclerView.ItemDecoration divider) {
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        if (divider != null)
            recyclerView.addItemDecoration(divider);
        return recyclerView;
    }

    @Override
    public void setBack(int resId) {
        backIv.setImageResource(resId);
    }

    @Override
    public void setRightImage(int resId) {
        setRightClick(resId, null);
    }

    @Override
    public void setRightText(String rightText) {
        setRightClick(rightText, null);
    }

    @Override
    public void setRightClick(String rightText, View.OnClickListener onClickListener) {
        rightTv.setText(rightText);
        rightTv.setVisibility(View.VISIBLE);
        if (onClickListener != null)
            rightTv.setOnClickListener(onClickListener);
    }

    @Override
    public void setRightClick(int resId, View.OnClickListener onClickListener) {
        rightIv.setImageResource(resId);
        rightIv.setVisibility(View.VISIBLE);
        if (onClickListener != null)
            rightIv.setOnClickListener(onClickListener);
    }
}
