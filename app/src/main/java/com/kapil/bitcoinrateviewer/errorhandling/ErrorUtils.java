package com.kapil.bitcoinrateviewer.errorhandling;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.kapil.bitcoinrateviewer.R;


public class ErrorUtils {

    public static String getFormattedErrorMessage(Context context, Throwable e) {
        e.printStackTrace();

        if (e instanceof HttpException) {
            return getErrorMessageFromResponseCode(context, ((HttpException) e).response().code());
        }

        return context.getApplicationContext().getString(R.string.something_wrong);


    }

    public static String getErrorMessageFromResponseCode(Context context, int errorCode) {

        switch (errorCode) {
            case 401:
                return context.getApplicationContext().getString(R.string.login_again);
            case 404:
                return context.getApplicationContext().getString(R.string.no_resource_found);
            case 501:
            case 502:
                return context.getApplicationContext().getString(R.string.something_wrong_at_the_server);
            default:
                return context.getApplicationContext().getString(R.string.something_wrong);
        }

    }

}
