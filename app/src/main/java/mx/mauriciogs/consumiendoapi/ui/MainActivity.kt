package mx.mauriciogs.consumiendoapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import mx.mauriciogs.consumiendoapi.R
import mx.mauriciogs.consumiendoapi.application.PersonajesApplication
import mx.mauriciogs.consumiendoapi.data.room.entity.PersonajeEntity
import mx.mauriciogs.consumiendoapi.databinding.ActivityMainBinding
import mx.mauriciogs.consumiendoapi.domain.CharacterUseCase
import mx.mauriciogs.consumiendoapi.domain.model.Characters

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels() {
        MainViewModel.MainViewModelFactory( (application as PersonajesApplication).repository)
    }

    private val favoritesFragment : FavoritesFragment by lazy { FavoritesFragment() }

    private lateinit var personajeActual : Characters

    private var mutableCharacterList = mutableListOf<Characters>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        mainViewModel.getAllCharacter(1)
        initObservers()
        initListener()
    }

    private fun initListener() {
        binding.button.setOnClickListener {
            addFavorite()
        }
    }

    private fun addFavorite() {
        // Se inserta el dato en la db con ROOM
        val personajeFav = PersonajeEntity(
            nombre = personajeActual.name,
            especie = personajeActual.specie
        )
        try {
            mainViewModel.insertPersonajeFavorito(personajeFav)
            openFavoritesFragment()
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error en el guardado: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openFavoritesFragment() {
        binding.materialCardView.visibility = View.GONE
        binding.button.visibility = View.GONE
        supportFragmentManager
            .beginTransaction()
            .add(binding.frameLayout.id, favoritesFragment)
            .commit()
    }

    private fun initObservers() {
        mainViewModel.characterList.observe(this) { characterList ->
            if (characterList != null) {
                mutableCharacterList.addAll(characterList)
                personajeActual = mutableCharacterList[0]
                setUI(personajeActual)
            }
        }
        mainViewModel.showError.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG)
        }
    }

    private fun setUI(personaje: Characters) {
        Glide.with(this@MainActivity)
            .load(personaje.image)
            .centerCrop()
            .into(binding.ivCharacter)
        binding.tvNameCharacter.text = personaje.name
        binding.tvGenderCharacter.text = personaje.gender
        binding.tvSpecieCharacter.text = personaje.specie
        binding.tvStateCharacter.text = personaje.state
    }
}