package br.com.accenture.training_android.model.pokedex

import com.google.gson.annotations.SerializedName

data class PokedexSpecies(
    @SerializedName("name")
    var pokemonName: String,
    @SerializedName("url")
    var pokemonUrl: String
)