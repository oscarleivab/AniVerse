package com.example.aniverse.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aniverse.R
import com.example.aniverse.model.Anime
import com.example.aniverse.viewmodel.AnimeViewModel

class AnimeAdapter(
    var animeList: List<Anime>,
    private val viewModel: AnimeViewModel,
    private val isFavoritesFragment: Boolean
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenAnime: ImageView = itemView.findViewById(R.id.imagenAnime)
        val nombreAnime: TextView = itemView.findViewById(R.id.nombreAnime)
        val generoAnime: TextView = itemView.findViewById(R.id.generoAnime)
        val estudiosAnime: TextView = itemView.findViewById(R.id.estudiosAnime)
        val botonFavorito: Button = itemView.findViewById(R.id.botonFavorito)
        val botonEliminarFavorito: Button = itemView.findViewById(R.id.botonEliminarFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]

        Glide.with(holder.itemView.context)
            .load(anime.imagen)
            .into(holder.imagenAnime)

        holder.nombreAnime.text = anime.nombre
        holder.generoAnime.text = anime.genero
        holder.estudiosAnime.text = anime.estudios

        if (isFavoritesFragment) {
            holder.botonFavorito.visibility = View.GONE
            holder.botonEliminarFavorito.visibility = View.VISIBLE
            holder.botonEliminarFavorito.setOnClickListener { viewModel.eliminarFavorito(anime) }
        } else {
            holder.botonFavorito.visibility = View.VISIBLE
            holder.botonEliminarFavorito.visibility = View.GONE
            holder.botonFavorito.setOnClickListener { viewModel.agregarFavorito(anime) }
        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply { putParcelable("anime", anime) }
            val action = if (isFavoritesFragment) {
                R.id.action_favoritesFragment_to_favoritesDetailFragment
            } else {
                R.id.action_homeFragment_to_animeDetailFragment
            }
            holder.itemView.findNavController().navigate(action, bundle)
        }
    }

    override fun getItemCount(): Int = animeList.size

    fun updateList(newList: List<Anime>) {
        animeList = newList
        notifyDataSetChanged()
    }
}
