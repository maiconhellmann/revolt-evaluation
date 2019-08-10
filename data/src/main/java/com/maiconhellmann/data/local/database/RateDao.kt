package com.maiconhellmann.data.local.database

import androidx.room.*
import com.maiconhellmann.data.local.model.RateCache
import io.reactivex.Single

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019 
 */
@Dao
interface RateDao {

    @Query("SELECT * FROM RateCache WHERE base=:base")
    fun getAllByBaseCurrency(base: String): Single<List<RateCache>>

    @Query("DELETE FROM RateCache WHERE base=:base")
    fun deleteAllByBaseCurrency(base: String)

    @Update
    fun update(rateCache: RateCache)

    @Insert
    fun insert(rateCache: RateCache)

    @Insert
    fun insertAll(list: List<RateCache>)

    @Transaction
    fun updateAll(base: String, list: List<RateCache>) {
        deleteAllByBaseCurrency(base)
        insertAll(list)
    }
}