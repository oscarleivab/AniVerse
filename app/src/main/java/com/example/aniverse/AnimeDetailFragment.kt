package com.example.aniverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.aniverse.model.Anime
import com.example.aniverse.databinding.FragmentAnimeDetailBinding

class AnimeDetailFragment : Fragment() {

    private var _binding: FragmentAnimeDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var anime: Anime

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el anime seleccionado de los argumentos
        anime = arguments?.getParcelable<Anime>("anime")!!

        // Mostrar la información del anime
        binding.nombreAnime.text = anime.nombre
        binding.generoAnime.text = anime.genero
        binding.estudiosAnime.text = anime.estudios

        // Cargar la imagen del anime
        Glide.with(requireContext())
            .load(anime.imagen)
            .into(binding.imagenAnime)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}