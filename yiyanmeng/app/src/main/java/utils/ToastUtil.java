package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Windows on 2019/11/1.
 */

public class ToastUtil {
    public static void show(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void show(Context context,String msg,int toast_type){
        Toast.makeText(context,msg,toast_type).show();
    }

}
