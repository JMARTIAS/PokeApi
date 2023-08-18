package com.example.pokeapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.pokeapi.R
import com.example.pokeapi.core.Resource
import com.example.pokeapi.data.remote.PokemonDataSource
import com.example.pokeapi.databinding.FragmentPokemonDetailBinding
import com.example.pokeapi.domain.PokemonRepositoryImp
import com.example.pokeapi.domain.RetrofitClient
import com.example.pokeapi.presentation.PokemonDetailsViewModel
import com.example.pokeapi.presentation.PokemonViewModel
import com.squareup.picasso.Picasso

class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {
    private lateinit var binding: FragmentPokemonDetailBinding
    private val args by navArgs<PokemonDetailFragmentArgs>()

    private val viewModel by viewModels<PokemonDetailsViewModel> {
        PokemonDetailsViewModel.PokemonDetailsViewModelFactory(
            PokemonRepositoryImp(
                PokemonDataSource(RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentPokemonDetailBinding.bind(view)
        binding.textView2.setText(args.pokemonName)


        viewModel.fetchPokemonDetails(args.pokemonName).observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Loading -> {
                    Picasso.get().load(R.drawable.baseline_access_time_filled_24)
                }
                is Resource.Success -> {
                    Picasso.get().load(it.data.sprites.front_default).into(binding.imgPokemon)

                }
                is Resource.Failure -> {
/*                    binding.pokemonLoadingRecycler.visibility=View.GONE*/
                }

            }
        })
    }
}