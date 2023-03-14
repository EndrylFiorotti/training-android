package br.com.accenture.training_android.repository

import br.com.accenture.training_android.api.PokedexEndpoint

class PokedexRepository (private val pokedexEndpoint: PokedexEndpoint) {
    suspend fun getPokedex(id: String) = pokedexEndpoint.getPokedex(id)
}