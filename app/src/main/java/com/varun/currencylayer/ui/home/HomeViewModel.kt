package com.varun.currencylayer.ui.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.varun.currencylayer.data.models.Currency
import com.varun.currencylayer.data.models.CurrencyTypes
import com.varun.currencylayer.data.models.CurrentExchangeRates
import com.varun.currencylayer.data.repositories.Repository
import com.varun.currencylayer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    companion object {
        private const val LAST_FETCH_TIMESTAMP = "LAST_FETCH_TIMESTAMP"
    }

    val amount = MutableLiveData<String>()

    private fun getCurrentTimeStampInMillis(): Long {
        return System.currentTimeMillis() / 1000
    }

    fun fetchCurrencyData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val period = 30 * 60 * 1000
            Log.e("Last Data Fetch Time", getLastDataFetchTime().toString())
            if ((getLastDataFetchTime() + period) > getCurrentTimeStampInMillis()) {
                emit(Resource.success(data = repository.getSavedExchangeRates()))
            } else {
                // It's been more than 30 min
                val exchangeRates = viewModelScope.async {
                    repository.getCurrentExchangeRates()
                }
                val currencyTypes = viewModelScope.async {
                    repository.getCurrencyTypes()
                }
                emit(
                    Resource.success(
                        data = assembleCurrencyData(
                            exchangeRates.await(),
                            currencyTypes.await()
                        )
                    )
                )
                setDataFetchTime(exchangeRates.await().timestamp)
            }
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred while loading data from server!"
                )
            )
        }
    }

    fun updateCurrencies(list: List<Currency>) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.updateAllExchangeRates(list)))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred while updating database!"
                )
            )
        }
    }

    private fun assembleCurrencyData(
        exchangeRates: CurrentExchangeRates,
        currencyTypes: CurrencyTypes
    ): ArrayList<Currency> {
        val list = ArrayList<Currency>()
        for (entry in currencyTypes.currencies) {
            val currency = Currency()
            currency.code = entry.key
            currency.name = entry.value
            currency.rate =
                exchangeRates.quotes[exchangeRates.source + currency.code]!! // make it -> USDINR
            list.add(currency)
        }
        return list
    }

    private fun getLastDataFetchTime(): Long {
        return sharedPreferences.getLong(LAST_FETCH_TIMESTAMP, -1)
    }

    private fun setDataFetchTime(timestamp: Long) {
        sharedPreferences.edit().putLong(LAST_FETCH_TIMESTAMP, timestamp).apply()
    }
}