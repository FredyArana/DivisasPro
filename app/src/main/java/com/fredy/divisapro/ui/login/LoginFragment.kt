package com.fredy.divisapro.ui.login

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fredy.divisapro.R
import com.fredy.divisapro.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    private var isPasswordVisible: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoginButton()
        observeLoginResult()
        setupPasswordToggle()
    }

    private fun initLoginButton() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.login(email, password)
            } else {
                Toast.makeText(context, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeLoginResult() {
        loginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result?.let {
                navigateToHome()
            } ?: run {
                Toast.makeText(context, "Login fallido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToHome() {
        val bundle = Bundle().apply {
            putString("username", binding.editTextUsername.text.toString())
        }
        findNavController().navigate(R.id.action_loginFragment_to_currencyFragment, bundle)
    }

    private fun setupPasswordToggle() {
        binding.ivTogglePassword.setOnClickListener {
            if (isPasswordVisible) {
                // Ocultar la contraseña
                binding.editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ivTogglePassword.setImageResource(R.drawable.ic_false)
            } else {
                // Mostrar la contraseña
                binding.editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ivTogglePassword.setImageResource(R.drawable.ic_true)
            }
            binding.editTextPassword.setSelection(binding.editTextPassword.text.length)
            isPasswordVisible = !isPasswordVisible
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}