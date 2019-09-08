package com.maiconhellmann.domain.repository

import com.maiconhellmann.domain.entity.Rate
import io.reactivex.Observable
import io.reactivex.Single

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */interface RateRepository {
    fun getRateByCurrency(base: String, baseValue: Double): Single<List<Rate>>
    fun fetchRates(base: String): Single<List<Rate>>
    fun fetchRates(): Observable<List<Rate>>
}