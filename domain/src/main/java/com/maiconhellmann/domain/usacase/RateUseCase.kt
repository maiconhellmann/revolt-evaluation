package com.maiconhellmann.domain.usacase

import com.maiconhellmann.domain.entity.Rate
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

    companion object {
        const val CURRENCY_DEFAULT_BASE = "EUR"
        const val CURRENCY_DEFAULT_VALUE = 1.0
    }

    /**
     * Get the currency calculated based on the value passed as a parameter
     */
    fun getCalculatedRateByBase(base: String? = null, currentValue: Double? = null): Single<List<Rate>> {
        return repository.getRateByCurrency(base ?: CURRENCY_DEFAULT_BASE, currentValue ?: CURRENCY_DEFAULT_VALUE)
            .subscribeOn(scheduler)
            .map { rateList->
                val percent = currentValue ?: CURRENCY_DEFAULT_VALUE
                //calculate
                rateList.forEach { rate->
                    //ignore base currency
                    if (rate.currency != rate.base) {
                        rate.value = if (percent <= 0.0) {
                            0.0
                        } else {
                            rate.value * percent
                        }
                    }
                }
                rateList
            }
    }

    /**
     * Fetch rates from remote API and updates local cache
     */
    fun fetchRates(base: String?= null): Single<List<Rate>> {
        return repository.fetchRates(base ?: CURRENCY_DEFAULT_BASE)
            .subscribeOn(scheduler)
    }
}