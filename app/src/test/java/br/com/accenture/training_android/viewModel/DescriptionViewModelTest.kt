@file:Suppress("DEPRECATION")

package br.com.accenture.training_android.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.accenture.training_android.api.PokemonEndpoint
import br.com.accenture.training_android.model.pokemon.Pokemon
import br.com.accenture.training_android.model.pokemon.pokemonImage.Image
import br.com.accenture.training_android.model.pokemon.pokemonImage.ImageUrl
import br.com.accenture.training_android.model.pokemon.pokemonImage.PokemonImg
import br.com.accenture.training_android.model.pokemon.pokemonMove.MoveName
import br.com.accenture.training_android.model.pokemon.pokemonMove.PokemonMove
import br.com.accenture.training_android.model.pokemon.pokemonType.PokemonType
import br.com.accenture.training_android.model.pokemon.pokemonType.TypeName
import br.com.accenture.training_android.repository.PokemonRepository
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

class DescriptionViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var pokemonEndpointMock: PokemonEndpoint
    private lateinit var repository: PokemonRepository
    private lateinit var viewModel: DescriptionViewModel
    private lateinit var observerPokemon: Observer<Pokemon>
    private val dispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        pokemonEndpointMock = mockk()
        repository = PokemonRepository(pokemonEndpointMock)
        viewModel = DescriptionViewModel(repository)
        observerPokemon = mockk(relaxed = true)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get description of a specific pokemon`() = runBlocking {
        viewModel.pokemonLiveData.observeForever(observerPokemon)
        val pokemon = Pokemon(
            1,
            "bulbasaur",
            "7",
            "69",
            typeList = arrayListOf(
                PokemonType(
                    1,
                    TypeName(
                        name = "grass"
                    )
                )
            ),
            image = PokemonImg(
                Image(
                    ImageUrl(
                        urlImg = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                    )
                )
            ),
            movesList = arrayListOf(
                PokemonMove(
                    move = MoveName(
                        "swords-dance"
                    )
                )
            )
        )

        coEvery { repository.getPokemon("1") } returns pokemon

        viewModel.getPokemon("1")

        coVerify {
            observerPokemon.onChanged(
                pokemon
            )
        }
    }
}