package utils;

import android.util.Log;

/**
 * Created by Windows on 2019/11/1.
 */

public class LogUtil {
    private final static boolean isDebug=true;
    public static void log(String tag,String msg){
        if (isDebug)
        Log.i(tag, msg);
    }
    public static void log(String msg){
        if (isDebug)
            Log.i("tag", msg);
    }
}
