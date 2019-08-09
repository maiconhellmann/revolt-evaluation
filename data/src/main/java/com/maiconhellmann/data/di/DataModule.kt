package com.maiconhellmann.data.di

import com.maiconhellmann.data.RateRepositoryImpl
import com.maiconhellmann.domain.repository.RateRepository
import org.koin.dsl.module

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */

val repositoryModule = module {
    //Car
    factory<RateRepository> {
        RateRepositoryImpl(cacheDataSource = get(), remoteDataSource = get())
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)
