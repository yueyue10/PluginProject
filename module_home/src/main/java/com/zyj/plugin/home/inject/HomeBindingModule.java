package com.zyj.plugin.home.inject;


import com.zyj.plugin.home.main.MainActivity;
import com.zyj.plugin.home.fragment.HomeFragment;
import com.zyj.plugin.home.news.NewsListActivity;
import com.zyj.plugin.home.news.PickedListActivity;
import com.zyj.plugin.home.news.detail.NewsDetailActivity;
import com.zyj.plugin.home.news.picture.ShowPictureAc;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeBindingModule {

    @ContributesAndroidInjector()
    abstract HomeFragment homefragment();

    @ContributesAndroidInjector()
    abstract MainActivity mainactivity();

    @ContributesAndroidInjector()
    abstract NewsDetailActivity newsdetailactivity();

    @ContributesAndroidInjector()
    abstract NewsListActivity newslistactivity();

    @ContributesAndroidInjector()
    abstract PickedListActivity pickedlistactivity();

}