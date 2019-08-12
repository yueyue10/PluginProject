package com.zyj.plugin.login.inject;


import com.zyj.plugin.login.login.LoginActivity;
import com.zyj.plugin.login.login.forgetpassword.ForgetPasswordActivity;
import com.zyj.plugin.login.login.register.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CommonBindingModule {

    @ContributesAndroidInjector()
    abstract LoginActivity loginactivity();

    @ContributesAndroidInjector()
    abstract RegisterActivity registeractivity();

    @ContributesAndroidInjector()
    abstract ForgetPasswordActivity forgetpasswordactivity();


}