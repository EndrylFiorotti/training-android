package br.com.accenture.training_android.model.pokemon.pokemonImage

import com.google.gson.annotations.SerializedName

data class PokemonImg(
    @SerializedName("other")
    var imgList: Image
)