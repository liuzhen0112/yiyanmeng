package base;

/**
 * Created by Windows on 2019/11/4.
 */

public interface ResultCallBack<T> {
    void onSuccess(T t);
    void onFail(String error);
}
