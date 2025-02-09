package com.example.aniverse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aniverse.model.Anime

class AnimeViewModel : ViewModel() {

    private val _favoritos = MutableLiveData<List<Anime>>(emptyList())
    val favoritos: LiveData<List<Anime>> get() = _favoritos

    fun agregarFavorito(anime: Anime) {
        _favoritos.value = _favoritos.value?.toMutableList()?.apply {
            if (!contains(anime)) add(anime)
        }
    }

    fun eliminarFavorito(anime: Anime) {
        _favoritos.value = _favoritos.value?.toMutableList()?.apply { remove(anime) }
    }
}
