package base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import view.BaseView;

/**
 * Created by Windows on 2019/11/4.
 */

public abstract class BaseActivity<V extends BaseView,T extends BasePresenter<V>> extends AppCompatActivity implements BaseView {
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        mPresenter=createPresenter();
        if (mPresenter!=null){
            mPresenter.bindView((V) this);
        }
        initView();
        setListener();
        initData();

    }

    protected abstract void initData();
    protected abstract void setListener();
    protected abstract void initView();
    protected abstract T createPresenter();
    protected abstract int bindLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDestroy();
            mPresenter=null;
        }
    }
}
