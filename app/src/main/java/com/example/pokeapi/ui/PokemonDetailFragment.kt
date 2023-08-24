package com.example.pokeapi.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.pokeapi.R
import com.example.pokeapi.core.Resource
import com.example.pokeapi.data.remote.PokemonDataSource
import com.example.pokeapi.databinding.FragmentPokemonDetailBinding
import com.example.pokeapi.domain.PokemonRepositoryImp
import com.example.pokeapi.domain.RetrofitClient
import com.example.pokeapi.presentation.PokemonDetailsViewModel
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
        binding = FragmentPokemonDetailBinding.bind(view)
        binding.textPokemonName.text = args.pokemonName.uppercase()

        viewModel.fetchPokemonDetails(args.pokemonName).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Picasso.get().load(R.drawable.baseline_access_time_filled_24).into(binding.imgPokemon)
                }
                is Resource.Success -> {
                    val pokemon = it.data

                    Picasso.get().load(pokemon.sprites.front_default).into(binding.imgPokemon)

                    val types = pokemon.types.joinToString(", ") { it.type.name }
                    binding.txtElementValue.text = types
                    binding.txtHeightValue.text = pokemon.height.toString()
                    binding.txtWeightValue.text = pokemon.weight.toString()
                    binding.txtHpValue.text = pokemon.stats[0].base_stat.toString()
                    binding.txtAttackValue.text = pokemon.stats[1].base_stat.toString()
                    binding.txtDefenseValue.text = pokemon.stats[2].base_stat.toString()

                    val moves = pokemon.moves.joinToString(" ,") { it.move.name }
                    binding.txtMovesNames.text = moves
                }
                is Resource.Failure -> {
                    /* Handle failure state if needed */
                }
            }
        })
    }
}