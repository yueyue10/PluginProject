package com.zyj.plugin.common.data.utils;


import android.widget.Toast;

import com.zyj.plugin.common.BaseApplication;
import com.zyj.plugin.common.data.bean.BaseResponse;
import com.zyj.plugin.common.data.bean.BaseResponseV2;
import com.zyj.plugin.common.uitl.CommonUtils;

import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by chao.qu at 2017/10/20
 *
 * @author quchao
 */

public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxFlSchedulerHelper() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
                    if (baseResponse.getResult() == BaseResponse.SUCCESS
                            && baseResponse.getObject() != null
                            && CommonUtils.isNetworkConnected(BaseApplication.getInstance().getApplicationContext())) {
                        return createData(baseResponse.getObject());
                    } else {
                        return Observable.error(new ServiceException(baseResponse.getResultMessage(), baseResponse.getResult()));
                    }
                });
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponseV2<T>, T> handleResultV2() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseResponseV2<T>, Observable<T>>) baseResponse -> {
                    if (baseResponse.getResult() == BaseResponse.SUCCESS
                            && baseResponse.getValue() != null
                            && CommonUtils.isNetworkConnected(BaseApplication.getInstance().getApplicationContext())) {
                        return createData(baseResponse.getValue());
                    } else {
                        return Observable.error(new ServiceException(baseResponse.getMessage(), baseResponse.getResult()));
                    }
                });
    }

    /**
     * 得到 Observable
     *
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }

}
