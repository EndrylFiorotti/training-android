package br.com.accenture.training_android.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.accenture.training_android.R
import br.com.accenture.training_android.adapter.PokedexListAdapter
import br.com.accenture.training_android.databinding.FragmentPokedexBinding
import br.com.accenture.training_android.model.pokedex.PokedexEntries
import br.com.accenture.training_android.viewModel.PokedexViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment(R.layout.fragment_pokedex), PokedexListAdapter.OnListenerPokedex {
    private val viewModel: PokedexViewModel by viewModel()
    private lateinit var binding: FragmentPokedexBinding
    var setFragment: (fragment: Fragment) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokedexItemObject()
    }

    private fun pokedexItemObject() {
        viewModel.getPokemonListToPokedex()
        viewModel.pokedexListLiveData.observe(viewLifecycleOwner) {
            binding.RecyclerView.adapter = context?.let {
                PokedexListAdapter(
                    it, viewModel.pokedexListLiveData.value!!, this
                )
            }
        }
    }

    override fun onClickPokedex(pokedexEntries: PokedexEntries) {
        val fragment = DescriptionFragment()
        val bundle = Bundle().apply { putInt("id", pokedexEntries.id) }
        fragment.arguments = bundle
        setFragment(fragment)
    }
}