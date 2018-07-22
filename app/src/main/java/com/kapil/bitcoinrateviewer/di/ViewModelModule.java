package com.kapil.bitcoinrateviewer.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.kapil.bitcoinrateviewer.BitCoinRateViewModel;

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
