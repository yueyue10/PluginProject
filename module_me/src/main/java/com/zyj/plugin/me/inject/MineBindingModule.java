package com.zyj.plugin.me.inject;


import com.zyj.plugin.me.fragment.MineFragment;
import com.zyj.plugin.me.main.MainActivity;
import com.zyj.plugin.me.order.OrderListActivity;
import com.zyj.plugin.me.order.detail.OrderDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MineBindingModule {

    @ContributesAndroidInjector()
    abstract MineFragment homefragment();

    @ContributesAndroidInjector()
    abstract MainActivity mainactivity();

    @ContributesAndroidInjector()
    abstract OrderListActivity orderlistactivity();

    @ContributesAndroidInjector()
    abstract OrderDetailActivity OrderDetailActivity();

}