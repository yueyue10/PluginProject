package com.zyj.plugin.inject;


import com.zyj.plugin.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppBindingModule {
    @ContributesAndroidInjector()
    abstract MainActivity mainactivity();

}