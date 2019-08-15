package com.zyj.plugin.home.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.zyj.plugin.home.mvvm.model.NewsListModel;
import com.zyj.plugin.home.mvvm.viewmodel.NewsListViewModel;

/**
 * Description: <HomeViewModelFactory><br>
 * Author:      mxdl<br>
 * Date:        2019/7/2<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class HomeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile HomeViewModelFactory INSTANCE;
    private final Application mApplication;

    public static HomeViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (HomeViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HomeViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private HomeViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsListViewModel.class)) {
            return (T) new NewsListViewModel(mApplication, new NewsListModel(mApplication));
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
