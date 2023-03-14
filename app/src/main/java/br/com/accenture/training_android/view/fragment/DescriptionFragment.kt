package br.com.accenture.training_android.view.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.accenture.training_android.R
import br.com.accenture.training_android.adapter.MovesListAdapter
import br.com.accenture.training_android.databinding.FragmentDescriptionBinding
import br.com.accenture.training_android.model.pokemon.Pokemon
import br.com.accenture.training_android.model.pokemon.pokemonMove.PokemonMove
import br.com.accenture.training_android.viewModel.DescriptionViewModel
import coil.load
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionFragment : Fragment(R.layout.fragment_description) {
    private lateinit var binding: FragmentDescriptionBinding
    private val viewModel: DescriptionViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveId()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.pokemonLiveData.observe(viewLifecycleOwner) {
            displayPokemon(it)
        }
    }

    private fun receiveId() {
        val bundle = arguments
        val id = bundle?.getInt("id").toString()
        viewModel.getPokemon(id)
    }

    private fun moveItemObject(moveList: ArrayList<PokemonMove>) {
        binding.RecyclerViewMoves.adapter = context?.let {
            MovesListAdapter(it, moveList)
        }
    }


    private fun displayPokemon(it: Pokemon) {
        binding.imageViewPokemon.load(it.image.imgList.type.urlImg)
        binding.textViewNamePokemon.text = it.name
        binding.textViewHeightPokemon.text = Html.fromHtml(
            "<b>Height</b> ${it.height.toDouble() / 10} m",
            Html.FROM_HTML_MODE_LEGACY
        )
        binding.textViewWeightPokemon.text = Html.fromHtml(
            "<b>Weight</b> ${it.weight.toDouble() / 10} kg",
            Html.FROM_HTML_MODE_LEGACY
        )
        viewModel.displayAboveTwo(
            it.typeList,
            isVisible = {
                binding.textViewType2.visibility = View.VISIBLE
                binding.textViewType1.text = it.typeList[0].type.name
                binding.textViewType2.text = it.typeList[1].type.name
            },
            isNotVisible = {
                binding.textViewType2.visibility = View.GONE
                binding.textViewType1.text = it.typeList[0].type.name
            }
        )
        moveItemObject(it.movesList)
    }
}