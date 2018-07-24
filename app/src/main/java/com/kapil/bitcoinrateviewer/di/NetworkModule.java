package com.kapil.bitcoinrateviewer.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kapil.bitcoinrateviewer.BuildConfig;
import com.kapil.core.commons.BaseUrlChangingInterceptor;

import java.util.Set;

import javax.inject.Singleton;

import bakshi.kapil.com.bitcoinpricetracker.Constants.ServerEndPoints;
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
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {


        return new Retrofit.Builder()
                .baseUrl(ServerEndPoints.BLOCKCHAIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(Gson gson) {

        OkHttpClient.Builder httpClient;
        httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.addInterceptor(BaseUrlChangingInterceptor.get());
        return httpClient.build();
    }

    @Provides
    @Singleton
    static Gson provideGson(Set<TypeAdapterFactory> typeAdapters) {
        final GsonBuilder builder = new GsonBuilder();

        for (TypeAdapterFactory factory : typeAdapters) {
            builder.registerTypeAdapterFactory(factory);
        }
        return builder.create();
    }

}
