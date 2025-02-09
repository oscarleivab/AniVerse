package com.example.aniverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aniverse.adapter.AnimeAdapter
import com.example.aniverse.databinding.FragmentFavoritesBinding
import com.example.aniverse.viewmodel.AnimeViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(AnimeViewModel::class.java)

        // Configurar el RecyclerView
        val adapter = AnimeAdapter(emptyList(), viewModel, isFavoritesFragment = true) // Adaptador para FavoritesFragment
        binding.recyclerFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFavorites.adapter = adapter

        // Observar cambios en la lista de favoritos
        viewModel.favoritos.observe(viewLifecycleOwner) { favoritos ->
            adapter.animeList = favoritos
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}