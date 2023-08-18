package com.example.pokeapi.domain

import com.example.pokeapi.data.model.*
import com.example.pokeapi.data.remote.PokemonDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.`when`

class PokemonRepositoryImpTest {

    private lateinit var mockDataSource: PokemonDataSource // Cambia val por lateinit var
    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setup() {
        mockDataSource = mock()
        pokemonRepository = PokemonRepositoryImp(mockDataSource)
    }

    @Test
    fun `getPokemonList should return expected result`() = runBlocking {
        // Given
        val pokemonList = listOf(
            Pokemon("Pikachu", "Electric"),
            Pokemon("Bulbasaur", "Grass"),
            Pokemon("Charmander", "Fire")
        )

        val expectedResponse = PokemonResponse(results = pokemonList, status = "Success")
        `when`(mockDataSource.getPokemonList()).thenReturn(expectedResponse)

        // When
        val result = pokemonRepository.getPokemonList()

        // Then
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getPokemonDetails should return expected result`() = runBlocking {
        // Given
        val pokemonName = "Pikachu"
        val expectedResponse = PokemonDetail(
            id = 25,
            name = "Pikachu",
            baseExperience = 112,
            height = 4,
            isDefault = true,
            order = 35,
            weight = 60,
            abilities = listOf(PokemonAbility(isHidden = false, slot = 1)),
            moves = listOf(PokemonMove(versionGroupDetails = listOf(PokemonMoveVersion(levelLearnedAt = 10)))),
            stats = listOf(PokemonStat(effort = 0, baseStat = 35)),
            types = listOf(PokemonType(slot = 1)),
            sprites = Sprites(
                back_default = "back_default_url",
                front_default = "front_default_url"
            )
        )
        `when`(mockDataSource.getPokemonDetails(pokemonName)).thenReturn(expectedResponse)

        // When
        val result = pokemonRepository.getPokemonDetails(pokemonName)

        // Then
        assertEquals(expectedResponse, result)
    }
}