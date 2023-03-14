@file:Suppress("DEPRECATION")

package br.com.accenture.training_android.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.accenture.training_android.api.PokedexEndpoint
import br.com.accenture.training_android.model.pokedex.Pokedex
import br.com.accenture.training_android.model.pokedex.PokedexEntries
import br.com.accenture.training_android.model.pokedex.PokedexSpecies
import br.com.accenture.training_android.repository.PokedexRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PokedexViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockPokedexEndpoint: PokedexEndpoint
    private lateinit var repository: PokedexRepository
    private lateinit var viewModel: PokedexViewModel
    private lateinit var observerPokedexEntries: Observer<ArrayList<PokedexEntries>>
    private val dispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        mockPokedexEndpoint = mockk()
        repository = PokedexRepository(mockPokedexEndpoint)
        viewModel = PokedexViewModel(repository)
        observerPokedexEntries = mockk(relaxed = true)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get list pokemon from api`() = runBlocking {
        viewModel.pokedexListLiveData.observeForever(observerPokedexEntries)
        coEvery { repository.getPokedex("1") } returns Pokedex(
            arrayListOf(
                PokedexEntries(
                    1,
                    PokedexSpecies(
                        "bulbasaur",
                        "https://pokeapi.co/api/v2/pokemon-species/1/"
                    )
                )
            )
        )

        viewModel.getPokemonListToPokedex()

        coVerify {
            observerPokedexEntries.onChanged(
                arrayListOf(
                    PokedexEntries(
                        1,
                        PokedexSpecies(
                            "bulbasaur",
                            "https://pokeapi.co/api/v2/pokemon-species/1/"
                        )
                    )
                )
            )
        }
    }
}