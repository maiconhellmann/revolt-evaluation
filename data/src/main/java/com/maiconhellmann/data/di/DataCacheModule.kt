package com.maiconhellmann.data.di

import com.maiconhellmann.data.local.database.RateDataBase
import com.maiconhellmann.data.local.source.RateCacheDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */

val cacheDataModule = module {

    single { RateDataBase.createDatabase(androidContext()) }

    factory {
        RateCacheDataSource(rateDao = get())
    }
}