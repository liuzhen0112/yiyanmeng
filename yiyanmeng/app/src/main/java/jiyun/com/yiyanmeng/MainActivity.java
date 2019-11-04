package jiyun.com.yiyanmeng;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import base.BaseActivity;
import presenter.MainPresenter;
import view.MainView;

public class MainActivity extends BaseActivity<MainView,MainPresenter> implements MainView{


    @Override
    public void showToast(String msg) {
        
    }

    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        BlankFragment blankFragment = new BlankFragment();
        manager.beginTransaction().add(R.id.fra,blankFragment)
                .show(blankFragment).commit();

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView() {



    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }


}
