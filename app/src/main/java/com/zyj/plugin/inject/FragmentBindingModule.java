package com.zyj.plugin.inject;


import com.zyj.plugin.fragment.HomeFragment;
import com.zyj.plugin.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @ContributesAndroidInjector()
    abstract MainActivity mainactivity();

    @ContributesAndroidInjector()
    abstract HomeFragment homefragment();

}