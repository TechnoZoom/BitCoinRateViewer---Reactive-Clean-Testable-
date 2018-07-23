package com.kapil.bitcoinrateviewer.di;

import com.kapil.bitcoinrateviewer.presentation.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentsBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}