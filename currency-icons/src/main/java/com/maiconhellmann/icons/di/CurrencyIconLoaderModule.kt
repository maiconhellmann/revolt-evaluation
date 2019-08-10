package com.maiconhellmann.icons.di

import com.maiconhellmann.icons.CurrencyIconLoader
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 10/08/2019
 * 
 * (c) 2019 
 */

val currencyIconLoaderModule = module {
    factory {
        CurrencyIconLoader(androidContext())
    }
}