package com.kapil.bitcoinrateviewer.di;

import com.kapil.bitcoinrateviewer.BitCoinRateViewFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract BitCoinRateViewFragment bindBitCoinRateViewFragment();



}
