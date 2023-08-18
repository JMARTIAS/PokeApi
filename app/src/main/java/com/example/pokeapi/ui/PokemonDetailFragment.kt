package com.example.pokeapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.pokeapi.R
import com.example.pokeapi.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {
    private lateinit var binding: FragmentPokemonDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentPokemonDetailBinding.bind(view)

    }

    companion object {
        fun fragmentPokemonDetailToPokemonDetailFragment(name: String): Any {

        }
    }

}