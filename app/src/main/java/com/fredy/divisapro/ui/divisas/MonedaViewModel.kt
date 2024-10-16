package com.fredy.divisapro.ui.divisas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fredy.divisapro.model.TipoCambio

class MonedaViewModel: ViewModel() {
    private val tipoCambio = listOf(
        TipoCambio("Dólares", 0.27),
        TipoCambio("Euros", 0.24),
        TipoCambio("Pesos Mexicanos", 5.29),
    )

    private val _conversionResult = MutableLiveData<String>()
    val conversionResult: MutableLiveData<String> = _conversionResult

    fun convertir(monto: Double, moneda: String) {
        val tasa = tipoCambio.find { it.moneda == moneda }?.tasa ?: 0.0
        val resultado = monto * tasa
        _conversionResult.value = "s/$monto nuevos soles son $resultado $moneda"
    }
}