package com.kapil.bitcoinrateviewer.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.kapil.bitcoinrateviewer.Constants.ReactiveConstants.ANDROID_SCHEDULER;
import static com.kapil.bitcoinrateviewer.Constants.ReactiveConstants.PROCESSING_SCHEDULER;


/**
 * Created by kapilbakshi on 21/07/18.
 */

@Module
public class RxModule {

    @Singleton
    @Named(ANDROID_SCHEDULER)
    @Provides
    public Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Named(PROCESSING_SCHEDULER)
    @Provides
    public Scheduler providesProcessingScheduler() {
        return Schedulers.io();
    }

}
