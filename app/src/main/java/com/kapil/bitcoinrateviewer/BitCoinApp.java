package com.kapil.bitcoinrateviewer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.kapil.bitcoinrateviewer.di.AppInjector;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by kapilbakshi on 21/07/18.
 */

public class BitCoinApp extends Application implements HasActivityInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static Context getBitCoinAppContext() {
        return context;
    }

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
        context = this;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
