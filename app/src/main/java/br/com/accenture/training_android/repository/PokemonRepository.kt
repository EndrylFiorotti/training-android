package br.com.accenture.training_android.repository

import br.com.accenture.training_android.api.PokemonEndpoint

class PokemonRepository (private val pokemonEndpoint: PokemonEndpoint) {
    suspend fun getPokemon(id: String) = pokemonEndpoint.getPokemon(id)
}