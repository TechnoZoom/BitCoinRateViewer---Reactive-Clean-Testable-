package com.kapil.bitcoinrateviewer.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.kapil.core.di.ViewModelFactory;
import com.kapil.core.di.ViewModelKey;

import bakshi.kapil.com.bitcoinpricetracker.data.BitCoinRateViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BitCoinRateViewModel.class)
    abstract ViewModel bindAllArticlesViewModel(BitCoinRateViewModel allArticlesViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
