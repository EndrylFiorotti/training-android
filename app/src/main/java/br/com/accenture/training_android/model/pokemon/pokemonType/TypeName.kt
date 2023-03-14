package br.com.accenture.training_android.model.pokemon.pokemonType

import com.google.gson.annotations.SerializedName

data class TypeName(
    @SerializedName("name")
    var name: String
)