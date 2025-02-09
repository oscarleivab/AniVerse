package com.example.aniverse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aniverse.databinding.FragmentTab1NoticeBinding

class Tab1NoticeFragment : Fragment() {
    private var _binding: FragmentTab1NoticeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTab1NoticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nombreUsuario = arguments?.getString("Nombre")
        binding.tvTab1.text = "BIENVENIDO A ANIVERSE, ${nombreUsuario ?: ""}"
    }
}