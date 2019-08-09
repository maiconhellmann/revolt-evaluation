package com.maiconhellmann.data.local.mapper

import com.maiconhellmann.data.local.model.RateCache
import com.maiconhellmann.domain.entity.Rate

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */object RateCacheMapper {
    fun mapToDomain(list: List<RateCache>): List<Rate> {
        return list.map { mapToDomain(it) }
    }

    private fun mapToDomain(cache: RateCache): Rate {
        return Rate(
            base = cache.base,
            currency = cache.currency,
            date = cache.date,
            value = cache.value
        )
    }

    fun mapFromDomain(domain: List<Rate>): List<RateCache> {
        return domain.map { RateCacheMapper.mapFromDomain(it) }
    }
    fun mapFromDomain(domain: Rate): RateCache {
        return RateCache(
            base = domain.base,
            currency = domain.currency,
            date = domain.date,
            value = domain.value
        )
    }
}