package com.fredy.divisapro.ui.divisas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fredy.divisapro.R
import com.fredy.divisapro.databinding.FragmentCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment(){

    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MonedaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monedas = resources.getStringArray(R.array.monedas)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, monedas)
        binding.spinnerCurrency.setAdapter(adapter)

        setupObservers()
        setupButtonClick()

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_currencyFragment_to_loginFragment)
        }
    }

    private fun setupObservers() {
        viewModel.conversionResult.observe(viewLifecycleOwner, Observer { result ->
            binding.tvResult.text = result
        })
    }

    private fun setupButtonClick() {
        binding.btnConvert.setOnClickListener {
            val monto = binding.etAmount.text.toString().toDoubleOrNull()
            val moneda = binding.spinnerCurrency.text.toString()

            if (monto != null) {
                viewModel.convertir(monto, moneda)
            } else {
                binding.tvResult.text = "Por favor, ingrese un valor válido"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}