package com.example.pokeapi.domain

import com.example.pokeapi.data.model.PokemonDetail
import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.data.remote.PokemonDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepositoryImp(private val dataSource: PokemonDataSource,private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : PokemonRepository {

    override suspend fun getPokemonList(): PokemonResponse {
        return withContext(ioDispatcher) {
            dataSource.getPokemonList()
        }
    }

    override suspend fun getPokemonDetails(pokemon: String): PokemonDetail =
        dataSource.getPokemonDetails(pokemon)
}