package br.com.accenture.training_android.view

import br.com.accenture.training_android.api.PokedexEndpoint
import br.com.accenture.training_android.api.PokemonEndpoint
import br.com.accenture.training_android.di.RetrofitObject
import kotlinx.coroutines.runBlocking

fun main() {
    val response = RetrofitObject.createNetworkService<PokemonEndpoint>()
    runBlocking {
        val pokemons = response.getPokemon("2")
        println(
            pokemons
        )
    }
}