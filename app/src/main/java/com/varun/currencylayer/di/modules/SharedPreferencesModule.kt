package com.varun.currencylayer.di.modules

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule(application: Application) {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("sharedPreferences", MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }
}