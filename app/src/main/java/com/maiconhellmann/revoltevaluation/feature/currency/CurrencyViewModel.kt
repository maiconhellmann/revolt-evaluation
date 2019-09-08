package com.maiconhellmann.revoltevaluation.feature.currency

import androidx.lifecycle.MutableLiveData
import com.maiconhellmann.domain.entity.Rate
import com.maiconhellmann.domain.usacase.RateUseCase
import com.maiconhellmann.revoltevaluation.feature.common.BaseViewModel
import com.maiconhellmann.revoltevaluation.feature.common.StateMachineObservable
import com.maiconhellmann.revoltevaluation.feature.common.StateMachineSingle
import com.maiconhellmann.revoltevaluation.feature.common.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 10/08/2019
 * 
 * (c) 2019 
 */
class CurrencyViewModel(private val useCase: RateUseCase, private val uiScheduler: Scheduler) :
    BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Rate>>>().apply {
        value = ViewState.Loading
    }

    fun fetchCurrency(base: String? = null, currentValue: Double?= null) {
        disposables += useCase.subscribeForUpdates()
            .observeOn(uiScheduler)
            .compose(StateMachineObservable())
            .subscribeBy(
                onNext = {
                    getCurrencyListByBase(base, currentValue)
                }
            )
    }

    fun getCurrencyListByBase(base: String? = null, currentValue: Double?= null) {
        disposables += useCase.getCalculatedRateByBase(base, currentValue)
            .observeOn(uiScheduler)
            .compose (StateMachineSingle())
            .subscribeBy (
                onSuccess = {
                    state.postValue(it)
                }
            )
    }
}