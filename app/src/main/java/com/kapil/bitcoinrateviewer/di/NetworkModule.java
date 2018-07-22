package com.kapil.bitcoinrateviewer.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kapil.bitcoinrateviewer.BaseUrlChangingInterceptor;
import com.kapil.bitcoinrateviewer.BitCoinTypeAdapterFactory;
import com.kapil.bitcoinrateviewer.BuildConfig;
import com.kapil.bitcoinrateviewer.Constants.ServerEndPoints;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kapilbakshi on 22/07/18.
 */


@Module
public class NetworkModule {

    @Singleton
    @Provides
    public BitCoinService provideBitCoinService(Gson gson) {

        OkHttpClient.Builder httpClient;
        httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.addInterceptor(BaseUrlChangingInterceptor.get());
        OkHttpClient okHttpClient = httpClient.build();
        return new Retrofit.Builder()
                .baseUrl(ServerEndPoints.BLOCKCHAIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(BitCoinService.class);
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(BitCoinTypeAdapterFactory.create())
                .create();


    }

}
