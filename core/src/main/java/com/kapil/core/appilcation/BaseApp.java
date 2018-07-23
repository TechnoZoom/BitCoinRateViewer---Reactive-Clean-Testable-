package com.kapil.core.appilcation;

import android.app.Application;
import android.content.Context;

/**
 * Created by kapilbakshi on 21/07/18.
 */

public class BaseApp extends Application {


    public static void init(Context context) {
        BaseApp.context = context;
    }

    public static Context getBaseAppContext() {
        return context;
    }

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
