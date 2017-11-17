package com.bolooo.artlesson.util;


import com.bolooo.artlesson.model.http.exception.ApiException;
import com.bolooo.artlesson.model.http.respone.MyHttpResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * rx管理工具类
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<MyHttpResponse<T>, T> handleMyResult() {   //compose判断结果
        return new FlowableTransformer<MyHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<MyHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<MyHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(MyHttpResponse<T> tMyHttpResponse) {
                        if(tMyHttpResponse.isSuccess()) {
                            return createData(tMyHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException("hhelo", 404));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一线程处理,并返回结果
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<MyHttpResponse<T>, T> rxSchedulerHandlerResult() {    //compose简化线程
        return new FlowableTransformer<MyHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<MyHttpResponse<T>> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).
                                flatMap(new Function<MyHttpResponse<T>, Flowable<T>>() {
                            @Override
                            public Flowable<T> apply( MyHttpResponse<T> tMyHttpResponse)  {
                                if(tMyHttpResponse.isSuccess()) {
                                    return createData(tMyHttpResponse.getData());
                                } else {
                                    return Flowable.error(new ApiException("错误", 404));
                                }
                            }
                        });
            }
        };
    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
