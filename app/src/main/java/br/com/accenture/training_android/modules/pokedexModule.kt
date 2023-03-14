package br.com.accenture.training_android.modules

import br.com.accenture.training_android.di.RetrofitObject
import br.com.accenture.training_android.repository.PokedexRepository
import br.com.accenture.training_android.viewModel.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokedexModule = module {
    factory {
        PokedexRepository(RetrofitObject.createNetworkService())
    }
    viewModel {
        PokedexViewModel(get())
    }
}