package com.maiconhellmann.data.remote.api

import com.maiconhellmann.data.remote.model.BaseRatePayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */interface RateApi {
    @GET("/latest")
    fun fetchRates(@Query("base") base: String): Single<BaseRatePayload>
}