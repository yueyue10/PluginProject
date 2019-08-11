package com.zyj.plugin.me.utils;

import android.content.Context;
import android.content.Intent;

import com.zyj.plugin.me.order.detail.OrderDetailActivity;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class JudgeUtils {

    public static void startOrderDetailAc(Context mActivity, String id) {
        mActivity.startActivity(new Intent(mActivity, OrderDetailActivity.class)
                .putExtra("orderCode", id));
    }
}
