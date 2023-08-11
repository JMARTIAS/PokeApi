package com.example.pokeapi.domain

import com.example.pokeapi.application.AppConstants
import com.example.pokeapi.data.model.PokemonDetail
import com.example.pokeapi.data.model.PokemonResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("pokemon/?limit=151")
    suspend fun getPokemonResponse(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") pokemon: String): PokemonDetail
}

object RetrofitClient {
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}