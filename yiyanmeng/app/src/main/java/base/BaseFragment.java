package base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utils.LayoutUtil;
import view.BaseView;

/**
 * Created by Windows on 2019/11/4.
 */

public abstract class BaseFragment<V extends BaseView,T extends BasePresenter<V>> extends Fragment implements BaseView{
    protected Context context;
    protected View view;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view = LayoutInflater.from(context).inflate(bindLayout(),container,false);
        }
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter=bindPresenter();
        if (mPresenter!=null){
            mPresenter.bindView((V) this);
        }
        initView(view);
        initListener();
        initData();
    }
    protected abstract   T bindPresenter();
    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView(View view);
    protected abstract int bindLayout();
}
