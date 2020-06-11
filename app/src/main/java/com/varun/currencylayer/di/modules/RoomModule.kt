package com.varun.currencylayer.di.modules

import android.app.Application
import androidx.room.Room
import com.varun.currencylayer.data.room.AppDatabase
import com.varun.currencylayer.data.room.RateDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

    private var appDatabase: AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.NAME).build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesRateDao(): RateDao {
        return appDatabase.getRateDao()
    }
}