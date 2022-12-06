package mx.mauriciogs.consumiendoapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.mauriciogs.consumiendoapi.data.network.ResultState
import mx.mauriciogs.consumiendoapi.domain.CharacterUseCase
import mx.mauriciogs.consumiendoapi.domain.model.Characters

class MainViewModel : ViewModel() {

    private val characterUseCase: CharacterUseCase = CharacterUseCase()

    private val _showError = MutableLiveData<String>()
    val showError : LiveData<String>
        get() = _showError

    // La variable _anyCharacter se actualiza cuando llegue un personaje
    val _anyCharacter = MutableLiveData<Characters>()
    // Variable que se observa desde el MainActivity
    val anyCharacter : LiveData<Characters>
        get() = _anyCharacter

    // **** Crear un par de variables que sirvan para enviar una lista de personajes
    //      al MainActivity que posteriormente sirva para el RecyclerView

    // **** Modifica esta función para que ahora se pase toda la lista de personajes a la nueva
    //      variable creada que servirá para el RecyclerView
    fun getAllCharacter(page: Int) {
        // Esta función obtiene todos los personajes de una página de manera asíncrona
        // y actualiza la variable _anyCharacter con el personaje 0
        viewModelScope.launch {
            when (val result = characterUseCase.getAllCharacter(page)) {
                is ResultState.Success -> {
                    _anyCharacter.postValue(result.data[0])
                    //obtainedCharacters(result.data)
                }
                is ResultState.Error -> gotAnError(result.message)
            }
        }
    }

    // Crear una función que me obtenga un personaje por ID de manera asíncrona utilizando una
    // función del caso de uso de personajes, luego pasar el personaje obtenido a la variable _anyCharacer

    // Función que pasa un mensaje de error a la variable _showError, misma que es observada en
    // MainActivity
    private fun gotAnError(messageError: String?) {
        _showError.postValue(messageError)
    }
}