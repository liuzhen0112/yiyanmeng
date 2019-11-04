package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Windows on 2019/11/1.
 */

public class LayoutUtil {

    /**
     *
     * @param context
     * @param layoutId
     * @return
     * 加载布局view
     */
    public static View findLayout(Context context,int layoutId){
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        return view;
    }
    public static View findLayout(Context context, int layoutId, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return view;


    }
}
