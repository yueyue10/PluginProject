package com.zyj.plugin.common.mvp.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ColorUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyj.plugin.common.R;

import butterknife.ButterKnife;

/**
 * Description: <BaseFragment><br>
 * Author:      gxl<br>
 * Date:        2018/1/15<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected static final String TAG = BaseDialogFragment.class.getSimpleName();
    protected View mView;
    protected Activity mActivity;

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(onBindLayout(), container, false);
        mActivity = this.getActivity();
        initBackground();
        ButterKnife.bind(this, mView);
        initView(mView);
        initListener();
        return mView;
    }

    private void initBackground() {
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(ColorUtils.setAlphaComponent(
                ColorUtils.getColor(R.color.black), setBackgroundAlpha()))); //设置dialog背景透明
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        assert window != null;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(setDialogGravity());
    }

    public void setStyle() {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //防止连续点击add多个fragment
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract float setBackgroundAlpha();

    protected abstract int setDialogGravity();

    public abstract int onBindLayout();

    public abstract void initView(View view);

    public abstract void initData();

    protected abstract void initListener();

    public RecyclerView initRecyclerView(int resId, BaseQuickAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = mView.findViewById(resId);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }
}
