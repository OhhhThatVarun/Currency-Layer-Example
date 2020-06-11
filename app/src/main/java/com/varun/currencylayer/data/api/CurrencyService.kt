package com.varun.currencylayer.data.api

import com.varun.currencylayer.data.models.CurrencyTypes
import com.varun.currencylayer.data.models.CurrentExchangeRates
import retrofit2.http.GET

interface CurrencyService {

    @GET("live")
    suspend fun getCurrentExchangeRates(): CurrentExchangeRates

    @GET("list")
    suspend fun getCurrencyTypes(): CurrencyTypes
}