package com.kapil.bitcoinrateviewer.di;

import android.support.annotation.NonNull;

import com.kapil.bitcoinrateviewer.BitCoinRateMapper;
import com.kapil.bitcoinrateviewer.Constants.ReactiveConstants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;


@Module
public class BitCoinRateModule {

    @Provides
    public BitCoinRateRepository provideBitCoinRepository(BitCoinService bitCoinService,
                                                          @Named(ReactiveConstants.ANDROID_SCHEDULER) Scheduler androidScheduler,
                                                          @Named(ReactiveConstants.PROCESSING_SCHEDULER) Scheduler processingScheduler,
                                                          @NonNull BitCoinRateMapper bitCoinRateMapper) {
        return new BitCoinRateRepository(bitCoinService, androidScheduler,processingScheduler, bitCoinRateMapper) ;
    }

}
