package com.maiconhellmann.data

import com.maiconhellmann.data.local.mapper.RateCacheMapper
import com.maiconhellmann.data.local.source.RateCacheDataSource
import com.maiconhellmann.data.remote.souce.RateRemoteDataSource
import com.maiconhellmann.domain.entity.Rate
import com.maiconhellmann.domain.repository.RateRepository
import io.reactivex.Single

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */class RateRepositoryImpl(
    private val cacheDataSource: RateCacheDataSource,
    private val remoteDataSource: RateRemoteDataSource
) : RateRepository {
    override fun getRateByCurrency(base: String): Single<List<Rate>> {
        return cacheDataSource.getRateByBaseCurrency(base)
    }

    override fun fetchRates(base: String): Single<List<Rate>> {
        return remoteDataSource.fetchRate(base).map {
            cacheDataSource.updateRates(RateCacheMapper.mapFromDomain(it))
            it
        }
    }
}