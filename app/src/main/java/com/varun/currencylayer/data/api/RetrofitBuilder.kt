package com.varun.currencylayer.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private var BASE_URL = "http://api.currencylayer.com/"

    private fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val currencyService: CurrencyService = getRetrofit().create(CurrencyService::class.java)
}