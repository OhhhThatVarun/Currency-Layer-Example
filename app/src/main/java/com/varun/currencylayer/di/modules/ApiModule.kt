package com.varun.currencylayer.di.modules

import com.varun.currencylayer.data.api.ApiKeyInterceptor
import com.varun.currencylayer.data.api.CurrencyService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
object ApiModule {

    private var BASE_URL = "http://api.currencylayer.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    @Provides
    @Singleton
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }
}