package com.zyj.plugin.common.data;

import com.zyj.plugin.common.BuildConfig;

/**
 * Description: <API><br>
 * Author:      mxdl<br>
 * Date:        2019/6/23<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class API {

    public static final String BASE_URL_DEV = "http://tx.enn.cn/";
    public static final String BASE_URL_TEST = "http://genius.enn.cn/";
    public static final String BASE_URL_RELEASE = "http://travel.enn.cn/";
    public static final String BASE_URL = BuildConfig.DEBUG ? BASE_URL_RELEASE : BASE_URL_RELEASE;
}
