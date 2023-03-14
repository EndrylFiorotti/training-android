package br.com.accenture.training_android.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentOnAttachListener
import br.com.accenture.training_android.R
import br.com.accenture.training_android.databinding.ActivityMainBinding
import br.com.accenture.training_android.view.fragment.DescriptionFragment
import br.com.accenture.training_android.view.fragment.PokedexFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokedexFragment: PokedexFragment
    private lateinit var descriptionFragment: DescriptionFragment
    private val toolbar: Toolbar get() = findViewById(R.id.toolbar_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokedexFragment = PokedexFragment()
        descriptionFragment = DescriptionFragment()
        setFragment(pokedexFragment)
        toolbarOnBackPressed()
    }

    init {
        val listener = FragmentOnAttachListener { _, fragment ->
            if (fragment is PokedexFragment) {
                fragment.setFragment = {
                    setFragment(it)
                }
            }
        }
        supportFragmentManager.addFragmentOnAttachListener(listener)
    }

    private fun toolbarOnBackPressed() {
        toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_fragment, fragment)
        fragmentTransaction.addToBackStack("Pokedex")
        fragmentTransaction.commit()
    }
}