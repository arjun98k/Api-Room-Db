package com.example.apiroomdb.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apiroomdb.R
import com.example.apiroomdb.databinding.FragmentLoginBinding
import com.example.apiroomdb.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                // Navigate to MainFragment
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                // Show error
                Toast.makeText(requireContext(), "Login failed. Check credentials.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}