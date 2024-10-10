package com.fredy.divisapro.model

data class ExchangeRatesResponse(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)