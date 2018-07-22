package com.kapil.bitcoinrateviewer.di;

import com.kapil.bitcoinrateviewer.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentsBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}