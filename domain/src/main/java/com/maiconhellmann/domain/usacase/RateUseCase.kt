package com.maiconhellmann.domain.usacase

import com.maiconhellmann.domain.entity.BaseRate
import com.maiconhellmann.domain.repository.RateRepository
import io.reactivex.Scheduler
import io.reactivex.Single

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */class RateUseCase(private val repository: RateRepository, private val scheduler: Scheduler) {
    fun getCalculatedRateByBase(base: String, currentValue: Double): Single<BaseRate> {
        //TODO calculations based on current value
        return repository.getRateByCurrency(base)
            .subscribeOn(scheduler)
    }
}