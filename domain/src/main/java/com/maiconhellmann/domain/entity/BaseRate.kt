package com.maiconhellmann.domain.entity

data class BaseRate(
    val base: String,
    val date: String,
    val rates: List<Rate>
)