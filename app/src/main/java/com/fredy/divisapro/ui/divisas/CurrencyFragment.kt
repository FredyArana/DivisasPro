package com.fredy.divisapro.ui.divisas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fredy.divisapro.R
import com.fredy.divisapro.databinding.FragmentCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment(R.layout.fragment_currency) {

    private lateinit var binding: FragmentCurrencyBinding
    private val viewModel: CurrencyViewModel by viewModels()

    private val apiKey = "A3Aw6wZdIiGQ2U8NNpBCXKX7WV9dO7Db"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrencyBinding.bind(view)

        // Listado de monedas disponibles en el Spinner
        val currencies = listOf("USD", "EUR", "MXN")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies)
        binding.spinnerCurrency.adapter = adapter

        // Acción del botón "Convertir"
        binding.btnConvert.setOnClickListener {
            val amount = binding.etAmount.text.toString().toDoubleOrNull() ?: 0.0
            val targetCurrency = binding.spinnerCurrency.selectedItem.toString()

            // Llamada al ViewModel para convertir la moneda, pasando la clave API
            viewModel.convertCurrency(apiKey, amount, targetCurrency)
        }

        // Observador para el resultado de la conversión
        viewModel.exchangeResult.observe(viewLifecycleOwner) { result ->
            binding.tvResult.text = "Resultado: $result"
        }

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_currencyFragment_to_loginFragment)
        }
    }
}