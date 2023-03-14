package br.com.accenture.training_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.accenture.training_android.model.pokemon.Pokemon
import br.com.accenture.training_android.model.pokemon.pokemonType.PokemonType
import br.com.accenture.training_android.repository.PokemonRepository
import kotlinx.coroutines.launch

class DescriptionViewModel(private val repository: PokemonRepository) : ViewModel() {
    private var _pokemonMutableLiveData = MutableLiveData<Pokemon>()
    val pokemonLiveData: LiveData<Pokemon> = _pokemonMutableLiveData

    fun getPokemon(id: String) {
        viewModelScope.launch {
            val pokemonInfo = repository.getPokemon(id)
            _pokemonMutableLiveData.postValue(
                Pokemon(
                    id = pokemonInfo.id,
                    name = pokemonInfo.name,
                    height = pokemonInfo.height,
                    weight = pokemonInfo.weight,
                    typeList = pokemonInfo.typeList,
                    image = pokemonInfo.image,
                    movesList = pokemonInfo.movesList
                )
            )
        }
    }

    fun displayAboveTwo(list: ArrayList<PokemonType>, isVisible: () -> Unit, isNotVisible: () -> Unit) {
        if (list.size > 1) isVisible()
        else isNotVisible()
    }
}