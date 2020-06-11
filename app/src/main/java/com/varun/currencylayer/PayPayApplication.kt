package com.varun.currencylayer

import android.app.Application
import com.varun.currencylayer.di.components.AppComponent
import com.varun.currencylayer.di.components.DaggerAppComponent
import com.varun.currencylayer.di.modules.ApiModule
import com.varun.currencylayer.di.modules.RoomModule
import com.varun.currencylayer.di.modules.SharedPreferencesModule

class PayPayApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .apiModule(ApiModule)
            .roomModule(RoomModule(this))
            .sharedPreferencesModule(SharedPreferencesModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}