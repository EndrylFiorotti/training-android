package br.com.accenture.training_android.modules

import br.com.accenture.training_android.di.RetrofitObject
import br.com.accenture.training_android.repository.PokemonRepository
import br.com.accenture.training_android.viewModel.DescriptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokemonModule = module {
    factory {
        PokemonRepository(
            RetrofitObject.createNetworkService()
        )
    }
    viewModel {
        DescriptionViewModel(get())
    }
}