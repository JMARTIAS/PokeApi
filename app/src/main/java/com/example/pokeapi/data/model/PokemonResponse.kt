package com.example.pokeapi.data.model

data class PokemonResponse(
    val message: List<String> = listOf(),
    val status: String,
    val results: List<Pokemon> = listOf()
)