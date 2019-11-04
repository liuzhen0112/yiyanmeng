package jiyun.com.yiyanmeng;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import base.BaseFragment;
import base.BaseObserver;
import bean.LoginBean;
import bean.ResultBean;
import jiyun.com.yiyanmeng.http.HttpManager;
import jiyun.com.yiyanmeng.http.MySerivce;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import presenter.MainPresenter;
import utils.RxUtil;
import view.MainView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment<MainView, MainPresenter> implements MainView{


    @Override
    public void showToast(String msg) {

    }


    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {



    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView(View view) {


    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_blank;
    }
}
