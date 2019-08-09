package com.maiconhellmann.data.remote.mapper

import com.maiconhellmann.data.local.model.RateCache
import com.maiconhellmann.data.remote.model.BaseRatePayload
import com.maiconhellmann.domain.entity.Rate

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */object RatePayloadMapper {
    fun mapToDomain(payload: BaseRatePayload): List<Rate>{
        return payload.rates::class.java.fields.map {
            val currency = it.name
            val value = it.getDouble(it)

            Rate(
                base = payload.base,
                date = payload.date,
                value = value,
                currency = currency
            )
        }
    }

    fun mapToCache(payload: BaseRatePayload): List<RateCache> {
        return payload.rates::class.java.fields.map {
            val currency = it.name
            val value = it.getDouble(it)

            RateCache(
                base = payload.base,
                date = payload.date,
                value = value,
                currency = currency
            )
        }
    }
}