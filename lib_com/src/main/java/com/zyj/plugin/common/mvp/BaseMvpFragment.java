package com.zyj.plugin.common.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zyj.plugin.common.mvp.presenter.BasePresenter;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Description: <BaseMvpFragment><br>
 * Author:      gxl<br>
 * Date:        2018/1/15<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    @Inject
    protected P mPresenter;

    @Override
    public void onAttach(Activity activity) {
//        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }
}
