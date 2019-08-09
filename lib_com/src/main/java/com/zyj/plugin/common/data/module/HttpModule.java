package com.zyj.plugin.common.data.module;


import android.text.TextUtils;
import android.util.Log;

import com.zyj.plugin.common.BuildConfig;
import com.zyj.plugin.common.data.API;
import com.zyj.plugin.common.data.ApiService;
import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.common.data.scope.AppUrl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    @Provides
    @Singleton
    DataManager provideDataManager(ApiService apiService) {
        return new DataManager(apiService);
    }

    @Singleton
    @Provides
    @AppUrl
    ApiService provideApiService(OkHttpClient client) {
        return createRetrofit(client, API.BASE_URL);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("appId", "2")
                    .addHeader("companyId", "6");
            if (!TextUtils.isEmpty(SpManager.getInstance().getToken()))
                requestBuilder.addHeader("X-Token", SpManager.getInstance().getToken());
            Log.d("HttpModule", SpManager.getInstance().getToken());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    private ApiService createRetrofit(OkHttpClient client, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }
}
