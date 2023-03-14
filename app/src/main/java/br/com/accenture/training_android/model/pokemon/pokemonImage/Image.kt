package br.com.accenture.training_android.model.pokemon.pokemonImage

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("official-artwork")
    var type: ImageUrl
)