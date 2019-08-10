package com.maiconhellmann.data.remote.souce

import com.maiconhellmann.data.remote.api.RateApi
import com.maiconhellmann.data.remote.mapper.RatePayloadMapper
import com.maiconhellmann.domain.entity.Rate
import io.reactivex.Single

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */class RateRemoteDataSource(private val api: RateApi) {
    fun fetchRate(base: String): Single<List<Rate>> {
        return api.fetchRates(base).map {
            RatePayloadMapper.mapToDomain(it)
        }
    }
}