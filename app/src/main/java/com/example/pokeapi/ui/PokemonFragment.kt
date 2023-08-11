package com.example.pokeapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.pokeapi.core.Resource
import com.example.pokeapi.data.remote.PokemonDataSource
import com.example.pokeapi.databinding.FragmentPokemonBinding
import com.example.pokeapi.domain.PokemonRepositoryImp
import com.example.pokeapi.domain.RetrofitClient
import com.example.pokeapi.presentation.PokemonViewModel
import androidx.navigation.fragment.findNavController
import com.example.pokeapi.data.model.Pokemon


class PokemonFragment : Fragment() {
    private lateinit var binding: FragmentPokemonBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private val pokemons = mutableListOf<Pokemon>()

    private val viewModel by viewModels<PokemonViewModel> {
        PokemonViewModel.PokemonViewModelFactory(
            PokemonRepositoryImp(
                PokemonDataSource(RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonBinding.bind(view)

        viewModel.fetchPokemonList().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    binding.pokemonLoadingRecycler.visibility = View.VISIBLE
                }
                is Resource.Success-> {
                    binding.pokemonLoadingRecycler.visibility = View.GONE
                    pokemons.clear()
                    pokemons.addAll(it.data.results)

                    initViews(pokemons)
                }
                is Resource.Failure -> {
                    binding.pokemonLoadingRecycler.visibility=View.GONE
                }
            }
        })
    }

    private fun initViews(list: List<Pokemon>){
        pokemonAdapter = PokemonAdapter(list){
            onItemSelected(it)
        }
        binding.rvPokemons.adapter = pokemonAdapter
        binding.rvPokemons.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }

    private fun onItemSelected(pokemon: Pokemon){
/*        val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
            pokemon.name
        )
        findNavController().navigate(action)*/
    }
}

