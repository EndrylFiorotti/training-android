package br.com.accenture.training_android.api

import br.com.accenture.training_android.model.pokemon.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonEndpoint {
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id")id: String): Pokemon
}