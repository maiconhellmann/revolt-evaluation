package com.maiconhellmann.data.local.source

import com.maiconhellmann.data.local.database.RateDao
import com.maiconhellmann.data.local.mapper.RateCacheMapper
import com.maiconhellmann.data.local.model.RateCache
import com.maiconhellmann.domain.entity.Rate
import io.reactivex.Single

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */class RateCacheDataSource(private val rateDao: RateDao) {
    fun getRateByBaseCurrency(base: String): Single<List<Rate>> {
        return rateDao.getAllByBaseCurrency(base).map { RateCacheMapper.mapToDomain(it) }
    }
    fun updateRates(list: List<RateCache>) {
        rateDao.updateAll(list)
    }
}