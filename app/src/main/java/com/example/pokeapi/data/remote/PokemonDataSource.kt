package com.example.pokeapi.data.remote

import com.example.pokeapi.data.model.PokemonDetail
import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.domain.WebService

class PokemonDataSource(private val webService: WebService) {
    suspend fun getPokemonList(): PokemonResponse = webService.getPokemonResponse()
    suspend fun getPokemonDetails(pokemon: String): PokemonDetail= webService.getPokemonDetails(pokemon)
}
