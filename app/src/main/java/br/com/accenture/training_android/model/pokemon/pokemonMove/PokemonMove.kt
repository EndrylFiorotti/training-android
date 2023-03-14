package br.com.accenture.training_android.model.pokemon.pokemonMove

import com.google.gson.annotations.SerializedName

data class PokemonMove(
    @SerializedName("move")
    var move: MoveName
)