package com.example.aniverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aniverse.adapter.AnimeAdapter
import com.example.aniverse.databinding.FragmentHomeBinding
import com.example.aniverse.model.Anime
import com.example.aniverse.viewmodel.AnimeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(AnimeViewModel::class.java)

        // Configurar el RecyclerView
        val animeList = listOf(
            Anime(
                "Naruto",
                "Acción, Aventura",
                "Studio Pierrot",
                "https://upload.wikimedia.org/wikipedia/en/9/94/NarutoCoverTankobon1.jpg"
            ),
            Anime(
                "Attack on Titan",
                "Acción, Drama",
                "Wit Studio",
                "https://upload.wikimedia.org/wikipedia/en/d/d6/Shingeki_no_Kyojin_manga_volume_1.jpg"
            ),
            Anime(
                "My Hero Academia",
                "Acción, Superhéroes",
                "Bones",
                "https://upload.wikimedia.org/wikipedia/en/6/6a/My_Hero_Academia_Volume_1.png"
            ),
            Anime(
                "Demon Slayer",
                "Acción, Fantasía",
                "Ufotable",
                "https://upload.wikimedia.org/wikipedia/en/0/09/Demon_Slayer_-_Kimetsu_no_Yaiba%2C_volume_1.jpg"
            ),
            Anime(
                "One Piece",
                "Aventura, Comedia",
                "Toei Animation",
                "https://upload.wikimedia.org/wikipedia/en/9/90/One_Piece%2C_Volume_61_Cover_%28Japanese%29.jpg"
            )
        )

        // Crear el adaptador para el HomeFragment (isFavoritesFragment = false)
        val adapter = AnimeAdapter(animeList, viewModel, isFavoritesFragment = false)
        binding.recyclerHome.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerHome.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}