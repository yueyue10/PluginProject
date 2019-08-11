package com.zyj.plugin.common.mvp.dialog;

import android.view.Gravity;
import android.view.View;

public abstract class BaseNorDialogFragment extends BaseDialogFragment {

    @Override
    protected float setBackgroundAlpha() {
        return 0.2f;
    }

    @Override
    protected int setDialogGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public void setStyle() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }
}
