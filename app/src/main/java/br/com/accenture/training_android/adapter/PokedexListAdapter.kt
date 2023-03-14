package br.com.accenture.training_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.accenture.training_android.R
import br.com.accenture.training_android.model.pokedex.PokedexEntries

class PokedexListAdapter(
    private val context: Context,
    private val pokemons: ArrayList<PokedexEntries>,
    private val onListenerPokedex: OnListenerPokedex
) : RecyclerView.Adapter<PokedexListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: ConstraintLayout = itemView.findViewById(R.id.container_view)

        fun bind(pokemon: PokedexEntries) {
            val name = itemView.findViewById<TextView>(R.id.textView_name_pokemon)
            name.text = pokemon.pokedexSpecies.pokemonName
        }
    }

    interface OnListenerPokedex {
        fun onClickPokedex(pokedexEntries: PokedexEntries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
        holder.cardView.setOnClickListener {
            onListenerPokedex.onClickPokedex(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemons.size

}