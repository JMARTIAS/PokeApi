package com.example.pokeapi.domain

import com.example.pokeapi.data.model.PokemonDetail
import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.data.remote.PokemonDataSource

class PokemonRepositoryImp(private val dataSource: PokemonDataSource) : PokemonRepository {

    override suspend fun getPokemonList(): PokemonResponse = dataSource.getPokemonList()

    override suspend fun getPokemonDetails(pokemon: String): PokemonDetail =
        dataSource.getPokemonDetails(pokemon)
}