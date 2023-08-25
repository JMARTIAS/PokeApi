package com.example.pokeapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.pokeapi.core.Resource
import com.example.pokeapi.domain.PokemonRepository
import kotlinx.coroutines.Dispatchers

class PokemonDetailsViewModel(private val repo: PokemonRepository) : ViewModel() {
    fun fetchPokemonDetails(pokemon: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPokemonDetails(pokemon)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    class PokemonDetailsViewModelFactory(private val repo: PokemonRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(PokemonRepository::class.java).newInstance(repo)
        }
    }
}