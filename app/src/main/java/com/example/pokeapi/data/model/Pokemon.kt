package com.example.pokeapi.data.model
data class NamedApiResource(
    val name: String,
    val url: String
) : ResourceSummary {
    constructor(name: String, category: String, id: Int) : this(name, resourceUrl(id, category))

    override val category by lazy { urlToCat(url) }
    override val id by lazy { urlToId(url) }
}

private fun urlToId(url: String): Int {
    return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
}

private fun urlToCat(url: String): String {
    return "/[a-z\\-]+/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isLetter() || it == '-' }
}

private fun resourceUrl(id: Int, category: String): String {
    return "/api/v2/$category/$id/"
}

interface ResourceSummary {
    val id: Int
    val category: String
}

data class ApiResource(
    val url: String
) : ResourceSummary {
    constructor(category: String, id: Int) : this(resourceUrl(id, category))

    override val category by lazy { urlToCat(url) }
    override val id by lazy { urlToId(url) }
}
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



    data class Name(
        val name: String,
        val language: NamedApiResource
    )
    data class Ability(
        val id: Int,
        val name: String,
        val isMainSeries: Boolean,
        val generation: NamedApiResource,
        val names: List<Name>,
        val flavorTextEntries: List<AbilityFlavorText>,
        val pokemon: List<AbilityPokemon>
    )


    data class AbilityFlavorText(
        val flavorText: String,
        val language: NamedApiResource,
        val versionGroup: NamedApiResource
    )

    data class AbilityPokemon(
        val isHidden: Boolean,
        val slot: Int,
        val pokemon: NamedApiResource
    )

    data class PokemonMove(
        val move: NamedApiResource,
        val versionGroupDetails: List<PokemonMoveVersion>
    )

    data class PokemonMoveVersion(
        val moveLearnMethod: NamedApiResource,
        val versionGroup: NamedApiResource,
        val levelLearnedAt: Int
    )

    data class PokemonStat(
        val stat: NamedApiResource,
        val effort: Int,
        val baseStat: Int
    )

    data class PokemonType(
        val slot: Int,
        val type: NamedApiResource
    )

    data class Stat(
        val id: Int,
        val name: String,
        val gameIndex: Int,
        val isBattleOnly: Boolean,
        val characteristics: List<ApiResource>,
        val moveDamageClass: NamedApiResource?,
        val names: List<Name>
    )

    data class Type(
        val id: Int,
        val name: String,
        val generation: NamedApiResource,
        val moveDamageClass: NamedApiResource?,
        val names: List<Name>,
        val pokemon: List<TypePokemon>,
        val moves: List<NamedApiResource>
    )

    data class TypePokemon(
        val slot: Int,
        val pokemon: NamedApiResource
    )

