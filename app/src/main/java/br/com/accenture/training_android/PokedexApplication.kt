package br.com.accenture.training_android

import android.app.Application
import br.com.accenture.training_android.modules.pokedexModule
import br.com.accenture.training_android.modules.pokemonModule
import org.koin.core.context.startKoin

class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            modules(pokedexModule, pokemonModule)
        }
    }
}