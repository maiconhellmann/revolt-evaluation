package com.maiconhellmann.data.common

import android.icu.util.Currency

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 10/08/2019
 * 
 * (c) 2019 
 */
object CurrencyDataProvider {
    fun getCurrency(key: String): String {
        return if (android.os.Build.VERSION.SDK_INT >= 24) {
            Currency.getInstance(key)?.displayName ?: ""
        } else {
            val c = java.util.Currency.getInstance(key)
            c?.displayName ?: ""
        }
    }
}