package com.maiconhellmann.data.remote.mapper

import com.maiconhellmann.data.local.model.RateCache
import com.maiconhellmann.data.remote.model.BaseRatePayload
import com.maiconhellmann.domain.entity.Rate
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */object RatePayloadMapper {
    fun mapToDomain(payload: BaseRatePayload): List<Rate>{
        return payload.rates::class.memberProperties.map {
            val currency = it.name
            val value = readInstanceProperty(payload.rates, currency) ?: 0.0

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

@Suppress("UNCHECKED_CAST")
fun <R> readInstanceProperty(instance: Any, propertyName: String): R {
    val property = instance::class.memberProperties
        // don't cast here to <Any, R>, it would succeed silently
        .first { it.name == propertyName } as KProperty1<Any, *>
    // force a invalid cast exception if incorrect type here
    return property.get(instance) as R
}