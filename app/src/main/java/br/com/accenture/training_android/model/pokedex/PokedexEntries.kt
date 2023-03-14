package br.com.accenture.training_android.model.pokedex

import com.google.gson.annotations.SerializedName

data class PokedexEntries(
    @SerializedName("entry_number")
    var id: Int,
    @SerializedName("pokemon_species")
    var pokedexSpecies: PokedexSpecies
)