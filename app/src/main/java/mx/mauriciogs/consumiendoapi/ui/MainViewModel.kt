package mx.mauriciogs.consumiendoapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = characterUseCase.getAllCharacter(page)) {
                is ResultState.Success -> obtainedCharacters(result.data)
                is ResultState.Error -> gotAnError(result.message)
            }
        }
    }

    private suspend fun gotAnError(messageError: String?) = withContext(Dispatchers.Main) {
        _showError.postValue(messageError)
    }

    private suspend fun obtainedCharacters(data: List<Characters>) = withContext(Dispatchers.Main) {
        characterList.postValue(data)
    }
}