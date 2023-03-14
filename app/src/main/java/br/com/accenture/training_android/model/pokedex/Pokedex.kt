package br.com.accenture.training_android.model.pokedex

import com.google.gson.annotations.SerializedName

data class Pokedex(
    @SerializedName("pokemon_entries")
    var listPokemons: ArrayList<PokedexEntries>
)