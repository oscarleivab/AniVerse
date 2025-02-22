package com.example.aniverse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.aniverse.databinding.FragmentFavoritesDetailBinding
import com.example.aniverse.model.Anime

class FavoritesDetailFragment : Fragment() {

    private var _binding: FragmentFavoritesDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var anime: Anime
    private val PREFS_NAME = "anime_comments"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el anime seleccionado de los argumentos
        anime = arguments?.getParcelable("anime")!!

        // Mostrar la información del anime
        with(binding) {
            nombreAnime.text = anime.nombre
            generoAnime.text = anime.genero
            estudiosAnime.text = anime.estudios
            Glide.with(requireContext()).load(anime.imagen).into(imagenAnime)

            // Cargar comentarios guardados
            val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            textoReseAs.text = sharedPreferences.getString(anime.nombre, getString(R.string.reseñasFavDetail))

            botonGuardarComentario.setOnClickListener {
                val comentario = editTextComentario.text.toString()
                if (comentario.isNotBlank()) {
                    val nuevoTexto = "${textoReseAs.text}\n- $comentario"
                    textoReseAs.text = nuevoTexto
                    editTextComentario.text.clear()

                    // Guardar comentario
                    sharedPreferences.edit().putString(anime.nombre, nuevoTexto).apply()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
