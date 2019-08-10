package com.maiconhellmann.revoltevaluation.di

import com.maiconhellmann.revoltevaluation.feature.currency.CurrencyAdapter
import com.maiconhellmann.revoltevaluation.feature.currency.CurrencyViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 10/08/2019
 * 
 * (c) 2019 
 */
val presentationModule = module {
    factory { CurrencyAdapter(currencyIconLoader = get()) }

    viewModel {
        CurrencyViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread())
    }
}