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
    fun map(list: List<RateCache>): List<Rate> {
        return list.map { map(it) }
    }

    private fun map(cache: RateCache): Rate {
        return Rate(
            base = cache.base,
            currency = cache.currency,
            date = cache.date,
            value = cache.value
        )
    }
}