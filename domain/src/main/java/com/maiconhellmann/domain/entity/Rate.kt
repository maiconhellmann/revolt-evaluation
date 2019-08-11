package com.maiconhellmann.domain.entity

data class Rate(
    val base: String,
    val date: String,
    val currency: String,
    val currencyDisplayName: String,
    var value: Double
)