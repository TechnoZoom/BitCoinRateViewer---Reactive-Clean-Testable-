package com.kapil.core.errorhandling;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.kapil.core.R;
import com.kapil.core.appilcation.BaseApp;


public class ErrorUtils {

    public static String getFormattedErrorMessage(Throwable e) {
        e.printStackTrace();

        if (e instanceof HttpException) {
            return getErrorMessageFromResponseCode(((HttpException) e).response().code());
        }

        return BaseApp.getBaseAppContext().getString(R.string.something_wrong);


    }

    public static String getErrorMessageFromResponseCode(int errorCode) {

        switch (errorCode) {
            case 401:
                return  BaseApp.getBaseAppContext().getApplicationContext().getString(R.string.login_again);
            case 404:
                return  BaseApp.getBaseAppContext().getApplicationContext().getString(R.string.no_resource_found);
            case 501:
            case 502:
                return  BaseApp.getBaseAppContext().getApplicationContext().getString(R.string.something_wrong_at_the_server);
            default:
                return  BaseApp.getBaseAppContext().getApplicationContext().getString(R.string.something_wrong);
        }

    }

}
