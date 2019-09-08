package com.maiconhellmann.domain.entity

data class Rate(
    val base: String,
    val date: String,
    val currency: String,
    val currencyDisplayName: String,
    var value: Double
) {
    override fun equals(other: Any?): Boolean {
        if(other == null || other !is Rate) return false

        return currency == other.currency && base == base
    }

    override fun hashCode(): Int {
        return currency.hashCode() + base.hashCode()
    }
}