package com.maiconhellmann.domain.di

import com.maiconhellmann.domain.usacase.RateUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */

val useCaseModule = module {
    factory {
        RateUseCase(repository = get(), scheduler = Schedulers.io())
    }
}

val domainModule = listOf(useCaseModule)