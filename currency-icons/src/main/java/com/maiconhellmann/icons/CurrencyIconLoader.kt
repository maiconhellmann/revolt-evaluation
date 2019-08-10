package com.maiconhellmann.icons

import android.content.Context
import android.widget.ImageView

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 10/08/2019
 * 
 * (c) 2019 
 */class CurrencyIconLoader(private val context: Context) {

    companion object {
        const val SUFFIX = "flag_"
    }

    fun load(currencyCode: String, imageView: ImageView) {
        val drawableResourceId =
            context.resources.getIdentifier(SUFFIX+currencyCode.toLowerCase(), "drawable", context.packageName)

        GlideApp.with(context).load(drawableResourceId).centerCrop().into(imageView)
    }
}