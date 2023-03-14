package br.com.accenture.training_android.model.pokemon.pokemonType

import com.google.gson.annotations.SerializedName

data class PokemonType(
    @SerializedName("slot")
    var slot: Int,
    @SerializedName("type")
    var type: TypeName
)