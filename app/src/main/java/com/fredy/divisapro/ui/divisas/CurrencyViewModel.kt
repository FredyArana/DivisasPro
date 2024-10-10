package com.fredy.divisapro.ui.divisas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredy.divisapro.network.ExchangeRatesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val exchangeRatesApi: ExchangeRatesApi
) : ViewModel() {

    private val _exchangeResult = MutableLiveData<Double>()
    val exchangeResult: LiveData<Double> get() = _exchangeResult

    fun convertCurrency(apiKey: String, amount: Double, targetCurrency: String) {
        viewModelScope.launch {
            try {
                val response = exchangeRatesApi.getExchangeRates(
                    apiKey = apiKey,
                    base = "PEN",  // Base como Soles Peruanos (PEN)
                    symbols = targetCurrency
                )
                if (response.isSuccessful) {
                    val rates = response.body()?.rates ?: emptyMap()
                    val rate = rates[targetCurrency] ?: 0.0
                    _exchangeResult.value = amount * rate
                }
            } catch (e: Exception) {
                // Manejo de error
            }
        }
    }
}