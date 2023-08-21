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
        var hp: Int=0
        var defense: Int=0
        var attack: Int=0
        var types: String=""
        var weight: Int=0
        var moves: String=""

        var typses: String=""
        viewModel.fetchPokemonDetails(args.pokemonName).observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Loading -> {
                    Picasso.get().load(R.drawable.baseline_access_time_filled_24)
                }
                is Resource.Success -> {
                    Picasso.get().load(it.data.sprites.front_default).into(binding.imgPokemon)

                    for (elem in it.data.types) {
                        types= elem.type.name+" "+types
                    }
                    binding.txtElementValue.setText(types)
                    weight=it.data.weight
                    binding.txtWeightValue.setText(weight)
                    hp=it.data.stats.get(0).baseStat
                    binding.txtHpValue.setText(hp)
                    it.data.stats.get(0).stat.name
                    attack=it.data.stats.get(1).baseStat
                    binding.txtAttackValue.setText(attack)
                    defense=it.data.stats.get(2).baseStat
                    binding.txtDefenseValue.setText(defense)

                    for (elem in it.data.moves) {
                        moves= elem.move.name+" "+moves
                    }
                    binding.txtMovesNames.setText(moves)
                }
                is Resource.Failure -> {
/*                    binding.pokemonLoadingRecycler.visibility=View.GONE*/
                }

            }

        })
    }
}