package com.maiconhellmann.data.local.mapper

import android.icu.util.Currency
import com.maiconhellmann.data.common.CurrencyDataProvider
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
            value = cache.value,
            currencyDisplayName = cache.currencyDisplayName
        )
    }

    fun mapFromDomain(domain: List<Rate>): List<RateCache> {
        return domain.map { mapFromDomain(it) }
    }
    private fun mapFromDomain(domain: Rate): RateCache {
        return RateCache(
            base = domain.base,
            currency = domain.currency,
            date = domain.date,
            value = domain.value,
            currencyDisplayName = CurrencyDataProvider.getCurrency(domain.currency)
        )
    }
}