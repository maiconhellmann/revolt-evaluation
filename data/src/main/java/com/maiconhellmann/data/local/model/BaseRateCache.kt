package com.maiconhellmann.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */
@Entity(primaryKeys = ["base", "currency"])
class RateCache(
    val base: String,
    val date: String,
    val currency: String,
    val value: Double
)