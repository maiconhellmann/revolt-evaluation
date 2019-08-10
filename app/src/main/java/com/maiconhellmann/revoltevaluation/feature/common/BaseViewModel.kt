package com.maiconhellmann.revoltevaluation.feature.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/*
 * This file is part of Revoltevaluation.
 *
 * Created by maiconhellmann on 10/08/2019
 *
 * (c) 2019
 */
open class BaseViewModel: ViewModel() {
    /**
     * Reference to disposables used in the viewModels. When the ViewModel is cleared, it disposes everything.
     */
    val disposables = CompositeDisposable()

    override fun onCleared() {
        //dispose everything
        disposables.clear()

        super.onCleared()
    }
}