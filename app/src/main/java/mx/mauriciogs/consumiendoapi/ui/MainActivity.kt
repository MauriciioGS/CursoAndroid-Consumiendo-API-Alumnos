package mx.mauriciogs.consumiendoapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import mx.mauriciogs.consumiendoapi.databinding.ActivityMainBinding
import mx.mauriciogs.consumiendoapi.domain.CharacterUseCase
import mx.mauriciogs.consumiendoapi.domain.model.Characters

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private var mutableCharacterList = mutableListOf<Characters>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        mainViewModel.getAllCharacter(1)
        initObservers()
    }

    private fun initObservers() {
        mainViewModel.characterList.observe(this) { characterList ->
            if (characterList != null) {
                mutableCharacterList.addAll(characterList)
                setUI(mutableCharacterList[0])
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