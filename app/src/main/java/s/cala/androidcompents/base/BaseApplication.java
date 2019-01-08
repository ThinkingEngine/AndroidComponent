package s.cala.androidcompents.base;

import android.app.Application;
import android.content.Context;

/**
 * package name:s.cala.androidcompents.base
 * create:cala
 * date:2019/1/8
 * commits:base application
 */
public class BaseApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
    }
}
