package base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Windows on 2019/11/4.
 */

public class BaseApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }
}
