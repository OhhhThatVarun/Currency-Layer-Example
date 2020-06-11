package com.varun.currencylayer.data.repositories

import com.varun.currencylayer.data.api.CurrencyService
import com.varun.currencylayer.data.models.Currency
import com.varun.currencylayer.data.room.RateDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: CurrencyService,
    private val rateDao: RateDao
) {

    // Remote-> Retrofit
    suspend fun getCurrentExchangeRates() = service.getCurrentExchangeRates()
    suspend fun getCurrencyTypes() = service.getCurrencyTypes()

    // Local-> Room
    suspend fun getSavedExchangeRates(): List<Currency> = rateDao.getAllList()
    suspend fun updateAllExchangeRates(saveList: List<Currency>) = rateDao.updateAllRate(saveList)
}