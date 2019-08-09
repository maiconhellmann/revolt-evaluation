package com.maiconhellmann.data.di

import com.maiconhellmann.data.BuildConfig
import com.maiconhellmann.data.remote.api.RateApi
import com.maiconhellmann.data.remote.souce.RateRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */

val remoteDataSourceModule = module {
    //OkHttp
    factory { providesOkHttpClient() }

    single {
        createWebService<RateApi>(
            okHttpClient = get(), url = BuildConfig.BASE_URL)
    }
    factory {
        RateRemoteDataSource(
            api = get())
    }
}

fun providesOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor)
        .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient, url: String
): T {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(url).client(okHttpClient)
        .build().create(T::class.java)
}