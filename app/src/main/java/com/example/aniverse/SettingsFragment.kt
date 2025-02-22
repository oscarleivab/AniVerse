package com.example.aniverse

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.aniverse.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cargar SharedPreferences
        sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Obtener nombre del usuario
        val userName = sharedPreferences.getString("username", "Invitado")
        binding.tvUserInfo.text = getString(R.string.usuarioSettings, userName)

        // Configurar el Switch de Modo Oscuro
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        binding.switchDarkMode.isChecked = isDarkMode

        // Listener para el Switch
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Cambiar el modo oscuro en toda la aplicación
            if (isChecked) {
                enableDarkMode()
                Toast.makeText(requireContext(), "Activando el modo oscuro...", Toast.LENGTH_SHORT).show()
            } else {
                disableDarkMode()
                Toast.makeText(requireContext(), "Activando el modo claro...", Toast.LENGTH_SHORT).show()
            }

            with(sharedPreferences.edit()){
                putBoolean("dark_mode", isChecked)
                apply()
            }

            actualizarTema()

        }
    }

    // Metodos para activar o desactivar el moo oscuro.
    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun actualizarTema() {
        binding.tvUserInfo.setTextColor(resources.getColor(if (binding.switchDarkMode.isChecked) R.color.white else R.color.black))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
