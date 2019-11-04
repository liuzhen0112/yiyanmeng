package presenter;

import android.util.Log;

import base.BasePresenter;
import base.ResultCallBack;
import model.MainModel;
import view.MainView;

/**
 * Created by Windows on 2019/11/4.
 */

public class MainPresenter extends BasePresenter<MainView> {
    private MainModel model;

    public MainPresenter() {
        model=new MainModel();
        addModel(model);
    }


}
