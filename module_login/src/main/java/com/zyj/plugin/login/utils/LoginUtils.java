package com.zyj.plugin.login.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.login.login.LoginActivity;

public class LoginUtils {
    public static boolean isLogin(Activity activity, int requestCode) {
        if (TextUtils.isEmpty(SpManager.getInstance().getToken())) {
            activity.startActivityForResult(new Intent(activity, LoginActivity.class), requestCode);
            return false;
        }
        return true;
    }
}
