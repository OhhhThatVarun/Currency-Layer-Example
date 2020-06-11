package com.varun.currencylayer.di.components

import com.varun.currencylayer.data.room.AppDatabase
import com.varun.currencylayer.data.room.RateDao
import com.varun.currencylayer.di.modules.ApiModule
import com.varun.currencylayer.di.modules.RoomModule
import com.varun.currencylayer.di.modules.SharedPreferencesModule
import com.varun.currencylayer.di.modules.ViewModelModule
import com.varun.currencylayer.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class, RoomModule::class, SharedPreferencesModule::class])
interface AppComponent {
    fun inject(homeFragment: HomeFragment)
    fun rateDao(): RateDao
    fun appDatabase(): AppDatabase
}