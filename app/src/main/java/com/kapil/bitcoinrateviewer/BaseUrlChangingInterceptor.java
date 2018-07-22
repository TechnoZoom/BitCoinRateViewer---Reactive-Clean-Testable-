package com.kapil.bitcoinrateviewer;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * An interceptor that allows runtime changes to the API Base URL in Retrofit.
 * The Base URL is set by calling the {@link BaseUrlChangingInterceptor#setInterceptor(String)} method.
 * */
//@Singleton
public class BaseUrlChangingInterceptor implements Interceptor {
    private static BaseUrlChangingInterceptor sInterceptor;
    private String mScheme;
    private String mHost;
    private HttpUrl httpUrl;

    public static BaseUrlChangingInterceptor get() {
        if (sInterceptor == null) {
            sInterceptor = new BaseUrlChangingInterceptor();
        }
        return sInterceptor;
    }

    private BaseUrlChangingInterceptor() {
    }


    public void setInterceptor(String url) {
        httpUrl = HttpUrl.parse(url);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        if(httpUrl != null) {
            original = original.newBuilder()
                    .url(httpUrl)
                    .build();
        }
        return chain.proceed(original);
    }
}
