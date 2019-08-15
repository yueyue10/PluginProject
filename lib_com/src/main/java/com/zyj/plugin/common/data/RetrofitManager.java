package com.zyj.plugin.common.data;

import android.app.Application;
import android.content.Context;

import com.zyj.plugin.common.data.utils.SSLContextUtil;

import java.io.IOException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: <RetrofitManager><br>
 * Author:      mxdl<br>
 * Date:        2019/6/22<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class RetrofitManager {

    public static RetrofitManager retrofitManager;
    public static Context mContext;
    OkHttpClient.Builder okHttpBuilder;
    private Retrofit mRetrofit;
    public String TOKEN;

    public static void init(Application application) {
        mContext = application;
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    private RetrofitManager() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.interceptors().add(logging);

        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null) {
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            okHttpBuilder.sslSocketFactory(socketFactory);
        }
        okHttpBuilder.hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        mRetrofit = new Retrofit.Builder().client(okHttpBuilder.build()).baseUrl(API.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    /**
     * 创建一个公共服务
     *
     * @return
     */
    public ApiService getApiService() {
        return mRetrofit.create(ApiService.class);
    }

    public void addToken(final String token) {
        if (okHttpBuilder != null)
            okHttpBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + token);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
    }
}