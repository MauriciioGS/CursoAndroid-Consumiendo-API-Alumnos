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

    val characterList = MutableLiveData<List<Characters>>()

    fun getAllCharacter(page: Int) {
        viewModelScope.launch {
            when (val result = characterUseCase.getAllCharacter(page)) {
                is ResultState.Success -> obtainedCharacters(result.data)
                is ResultState.Error -> gotAnError(result.message)
            }
        }
    }

    private fun gotAnError(messageError: String?) {
        _showError.postValue(messageError)
    }

    private fun obtainedCharacters(data: List<mx.mauriciogs.consumiendoapi.domain.model.Characters>) {
        characterList.postValue(data)
    }
}