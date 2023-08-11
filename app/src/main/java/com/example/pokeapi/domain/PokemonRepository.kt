package com.example.pokeapi.domain

import com.example.pokeapi.data.model.PokemonDetail
import com.example.pokeapi.data.model.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemonList(): PokemonResponse
    suspend fun getPokemonDetails(pokemon: String): PokemonDetail
}