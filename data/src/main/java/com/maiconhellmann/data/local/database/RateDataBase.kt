package com.maiconhellmann.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maiconhellmann.data.local.model.RateCache

/*
 * This file is part of Revoltevaluation.
 * 
 * Created by maiconhellmann on 09/08/2019
 * 
 * (c) 2019
 */
@Database(version = 1, entities = [RateCache::class], exportSchema = false)
abstract class RateDataBase : RoomDatabase() {
    abstract fun rateDao(): RateDao

    companion object {
        fun createDatabase(context: Context): RateDao {
            return Room.databaseBuilder(context, RateDataBase::class.java, "rate.db")
                .fallbackToDestructiveMigration().build().rateDao()
        }

        fun createDatabaseInMemory(context: Context): RateDao {
            return Room.inMemoryDatabaseBuilder(context, RateDataBase::class.java)
                .fallbackToDestructiveMigration().build().rateDao()
        }
    }
}