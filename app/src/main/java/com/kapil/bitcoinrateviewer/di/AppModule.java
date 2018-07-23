package com.kapil.bitcoinrateviewer.di;


import com.kapil.core.di.RxModule;

import bakshi.kapil.com.bitcoinpricetracker.data.BitCoinRateModule;
import dagger.Module;


@Module(includes = {ViewModelModule.class, BitCoinRateModule.class, NetworkModule.class,
        RxModule.class} )
public class AppModule {

}
