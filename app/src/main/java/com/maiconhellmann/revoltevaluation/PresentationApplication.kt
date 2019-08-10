package com.maiconhellmann.revoltevaluation

import android.app.Application
import com.maiconhellmann.data.di.dataModules
import com.maiconhellmann.domain.di.domainModule
import com.maiconhellmann.revoltevaluation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 10/08/2019
 * 
 * (c) 2019 
 */class PresentationApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@PresentationApplication)
            // declare modules
            modules(dataModules + domainModule + presentationModule)
        }
    }
}