package com.kapil.bitcoinrateviewer.di;

import android.app.Application;

import com.kapil.bitcoinrateviewer.appilcation.BitCoinApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
 interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
    void inject(BitCoinApp nycApp);
}
