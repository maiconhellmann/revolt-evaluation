package com.maiconhellmann.data.remote.model

data class BaseRatePayload(
    val base: String,
    val date: String,
    val rates: RatesPayload
)