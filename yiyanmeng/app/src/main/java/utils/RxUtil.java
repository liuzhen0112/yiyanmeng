package utils;

import android.util.Log;

import bean.ApiException;
import bean.ResultBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2019/11/4.
 */

public class RxUtil {


    //RxJava切换线程
    public static <T> ObservableTransformer<T,T> changeThread(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }
    //RxJava改变输出结果
    public static <T> ObservableTransformer<ResultBean<T>,T> changeResult(){
        return new ObservableTransformer<ResultBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResultBean<T>> upstream) {
                return upstream.flatMap(new Function<ResultBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(ResultBean<T> tResultBean) throws Exception {
                        String ret = tResultBean.getRet();
                        int errorCode = Integer.parseInt(ret);
                        if (errorCode==200){


                            return createObservable(tResultBean.getInfo()) ;
                        }else {
                            return Observable.error(new ApiException(errorCode,tResultBean.getMas()));
                        }

                    }
                });
            }
        };

    }

    //创建被观察者对象
    public static <T> Observable<T> createObservable(final T t){
       return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);

                }


            }
        });
    }


}
