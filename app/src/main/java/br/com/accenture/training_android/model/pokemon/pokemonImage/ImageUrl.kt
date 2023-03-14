package br.com.accenture.training_android.model.pokemon.pokemonImage

import com.google.gson.annotations.SerializedName

data class ImageUrl(
    @SerializedName("front_default")
    var urlImg: String
)