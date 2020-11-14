package com.thinhlh.testvnexpress.di.module;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.thinhlh.testvnexpress.BuildConfig;
import com.thinhlh.testvnexpress.data.service.VNExpressService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import timber.log.Timber;

/**
 * Created by Thinh Lai on 22/07/2019
 * Copyright (c) 2019 . All rights reserved.
 */
@Module
public class NetworkModule {

    private final Context context;
    private final String baseUrl;

    public NetworkModule(final Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                     StethoInterceptor stethoInterceptor,
                                     ChuckInterceptor chuckInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(chuckInterceptor);
            httpClientBuilder.addInterceptor(httpLoggingInterceptor);
            httpClientBuilder.addNetworkInterceptor(stethoInterceptor);
        }
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        });
        return httpClientBuilder.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor(message -> Timber.d(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    ChuckInterceptor provideChuckInterceptor() {
        return new ChuckInterceptor(context);
    }

    @Provides
    @Singleton
    VNExpressService provideVNExpressService(Retrofit retrofit) {
        return retrofit.create(VNExpressService.class);
    }

}
