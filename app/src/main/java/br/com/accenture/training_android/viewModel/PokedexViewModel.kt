package br.com.accenture.training_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.accenture.training_android.model.pokedex.PokedexEntries
import br.com.accenture.training_android.repository.PokedexRepository
import kotlinx.coroutines.launch

class PokedexViewModel(private val repository: PokedexRepository) : ViewModel() {
    private var _pokedexListMutableLiveData = MutableLiveData<ArrayList<PokedexEntries>>()
    val pokedexListLiveData: LiveData<ArrayList<PokedexEntries>> = _pokedexListMutableLiveData

    fun getPokemonListToPokedex() {
        viewModelScope.launch {
            _pokedexListMutableLiveData.postValue(repository.getPokedex("1").listPokemons)
        }
    }
}