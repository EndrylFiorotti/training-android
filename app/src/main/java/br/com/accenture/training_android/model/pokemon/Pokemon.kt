package br.com.accenture.training_android.model.pokemon

import br.com.accenture.training_android.model.pokemon.pokemonImage.PokemonImg
import br.com.accenture.training_android.model.pokemon.pokemonMove.PokemonMove
import br.com.accenture.training_android.model.pokemon.pokemonType.PokemonType
import com.google.gson.annotations.SerializedName


data class Pokemon(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("height")
    var height: String,
    @SerializedName("weight")
    var weight: String,
    @SerializedName("types")
    var typeList: ArrayList<PokemonType>,
    @SerializedName("sprites")
    var image: PokemonImg,
    @SerializedName("moves")
    var movesList: ArrayList<PokemonMove>
)