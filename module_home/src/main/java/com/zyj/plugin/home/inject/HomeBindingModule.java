package com.zyj.plugin.home.inject;


import com.zyj.plugin.home.fragment.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeBindingModule {

    @ContributesAndroidInjector()
    abstract HomeFragment homefragment();

}