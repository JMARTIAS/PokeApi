package com.example.pokeapi.domain

import com.example.pokeapi.data.model.NamedApiResource
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.data.remote.PokemonDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PokemonRepositoryImpTests {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var mockDataSource: PokemonDataSource
    private lateinit var pokemonRepository: PokemonRepository

    @BeforeEach
    fun setup() {
        mockDataSource = mockk()
        pokemonRepository = PokemonRepositoryImp(mockDataSource, testDispatcher)
    }

    @Test
    fun `getPokemonList should return expected result`() = testScope.runTest {
        // Given
        val pokemonList = listOf(
            Pokemon("Pikachu", "Electric"),
            Pokemon("Bulbasaur", "Grass"),
            Pokemon("Charmander", "Fire")
        )

        val expectedResponse = PokemonResponse(results = pokemonList, status = "Success", apiResults = listOf(
            NamedApiResource(name = "", url ="")
        ))
        coEvery { mockDataSource.getPokemonList() } returns expectedResponse

        // When
        val result = pokemonRepository.getPokemonList()

        // Then
        assertEquals(expectedResponse, result)
    }
}