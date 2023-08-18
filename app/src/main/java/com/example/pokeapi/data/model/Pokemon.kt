package com.example.pokeapi.data.model

data class Pokemon(
    val name: String,
    val url: String
)

    data class PokemonDetail(
        val id: Int,
        val name: String,
        val baseExperience: Int,
        val height: Int,
        val isDefault: Boolean,
        val order: Int,
        val weight: Int,
        val abilities: List<PokemonAbility>,
        val moves: List<PokemonMove>,
        val stats: List<PokemonStat>,
        val types: List<PokemonType>,
        val sprites: Sprites
    )

    data class Sprites(
        val back_default: String,
        val front_default: String
    )

    data class PokemonAbility(
        val isHidden: Boolean,
        val slot: Int
    )

    data class PokemonMove(
        val versionGroupDetails: List<PokemonMoveVersion>
    )

    data class PokemonStat(
        val effort: Int,
        val baseStat: Int
    )

    data class PokemonType(
        val slot: Int
    )

    data class PokemonMoveVersion(
        val levelLearnedAt: Int
    )

