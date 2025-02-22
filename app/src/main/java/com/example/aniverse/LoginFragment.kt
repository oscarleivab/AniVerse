package com.example.aniverse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.aniverse.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val nombreUsuario = binding.editTextText.text.toString()

            Toast.makeText(requireContext(), "Iniciada sesión en el usuario: $nombreUsuario", Toast.LENGTH_SHORT).show()

            // Guardar el nombre del usuario
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
            sharedPreferences.edit().putString("username", nombreUsuario).apply()

            val bundle = Bundle().apply {
                putString("Nombre", nombreUsuario)
            }

            findNavController().navigate(R.id.action_loginFragment_to_noticeFragment, bundle)
        }
    }
}