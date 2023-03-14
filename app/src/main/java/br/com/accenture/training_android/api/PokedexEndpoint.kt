package br.com.accenture.training_android.api

import br.com.accenture.training_android.model.pokedex.Pokedex
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexEndpoint {
    @GET("pokedex/{id}")
    suspend fun getPokedex(@Path("id")id: String): Pokedex
}