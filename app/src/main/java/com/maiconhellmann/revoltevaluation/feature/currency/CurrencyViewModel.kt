package com.maiconhellmann.revoltevaluation.feature.currency

import com.maiconhellmann.domain.usacase.RateUseCase
import com.maiconhellmann.revoltevaluation.feature.common.BaseViewModel
import io.reactivex.Scheduler

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 10/08/2019
 * 
 * (c) 2019 
 */
class CurrencyViewModel(private val useCase: RateUseCase, private val uiScheduler: Scheduler) :BaseViewModel() {

}