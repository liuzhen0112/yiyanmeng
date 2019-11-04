package base;

import bean.ApiException;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import utils.LogUtil;

/**
 * Created by Windows on 2019/11/4.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);

    }

    @Override
    public void onError(Throwable e) {

        //网络请求错误结果进行统一化处理
        if (e instanceof ApiException){
            ApiException apiException= (ApiException) e;
            LogUtil.log(apiException.getErrorCode()+apiException.getErrorMsg());
            onFail(apiException.getErrorCode()+apiException.getErrorMsg());
        }else {
            LogUtil.log(e.toString());
            onFail(e.toString());
        }

    }

    @Override
    public void onComplete() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }


    }
    public abstract void onSuccess(T t);
    public abstract void onFail(String error);
}
