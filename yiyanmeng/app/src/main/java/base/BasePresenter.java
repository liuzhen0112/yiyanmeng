package base;

import java.util.ArrayList;
import java.util.List;

import view.BaseView;

/**
 * Created by Windows on 2019/11/4.
 */

public abstract class BasePresenter<V extends BaseView> {
    protected V mView;
    protected List<BaseModel> models;

    protected void addModel(BaseModel model) {
        if (models == null) {
            models = new ArrayList<>();
        }
        models.add(model);

    }

    public V bindView(V v) {
        mView = v;
        return mView;
    }

    protected void onDestroy() {
        if (models != null && models.size() > 0) {
            for (BaseModel model : models) {
                model.onDestroy();
            }
            models.clear();
            models = null;
        }
        if (mView != null) {
            mView = null;
        }
    }



}
