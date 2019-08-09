package com.maiconhellmann.data.local.source

import com.maiconhellmann.data.local.database.RateDao
import com.maiconhellmann.data.local.model.RateCache
import io.reactivex.Single

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */class RateCacheDataSource(private val rateDao: RateDao) {
    fun getRateByBaseCurrency(base: String): Single<List<RateCache>> {
        return rateDao.getAllByBaseCurrency(base)
    }
}