package com.kapil.bitcoinrateviewer.di;


import dagger.Module;


@Module(includes = {ViewModelModule.class, BitCoinRateModule.class, NetworkModule.class,
        RxModule.class} )
public class AppModule {

}
