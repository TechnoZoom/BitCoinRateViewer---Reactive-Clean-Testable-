package com.kapil.bitcoinrateviewer.di;

import bakshi.kapil.com.bitcoinpricetracker.presentation.BitCoinRateViewFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract BitCoinRateViewFragment bindBitCoinRateViewFragment();



}
