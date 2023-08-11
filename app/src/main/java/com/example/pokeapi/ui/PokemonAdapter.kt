package com.example.pokeapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.R
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.databinding.PokemonItemBinding

class PokemonAdapter(
    var pokemonDataSet: List<Pokemon>,
    private val onItemClickListener: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(view: View) :
        RecyclerView.ViewHolder(view){
        private val binding=PokemonItemBinding.bind(view)

        fun render(pokemon: Pokemon, onItemClickListener: (Pokemon) -> Unit) {
            binding.pokemonName.text = pokemon.name
            itemView.setOnClickListener { onItemClickListener(pokemon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.pokemon_item, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.render(pokemonDataSet[position], onItemClickListener)
    }

    override fun getItemCount(): Int = pokemonDataSet.size

}

