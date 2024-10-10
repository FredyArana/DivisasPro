package com.fredy.divisapro.network

import com.fredy.divisapro.model.ExchangeRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ExchangeRatesApi {
    @GET("live")
    suspend fun getExchangeRates(
        @Header("apikey") apiKey: String,  // Header para la clave de API
        @Query("base") base: String = "PEN",  // Moneda base (en tu caso, "PEN")
        @Query("symbols") symbols: String = "USD,EUR,MXN"  // Monedas objetivo
    ): Response<ExchangeRatesResponse>
}