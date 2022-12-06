package mx.mauriciogs.consumiendoapi.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.mauriciogs.consumiendoapi.data.CharactersRepositoryLocal
import mx.mauriciogs.consumiendoapi.data.network.ResultState
import mx.mauriciogs.consumiendoapi.data.room.db.PresonajesFavDao
import mx.mauriciogs.consumiendoapi.data.room.entity.PersonajeEntity
import mx.mauriciogs.consumiendoapi.domain.CharacterUseCase
import mx.mauriciogs.consumiendoapi.domain.model.Characters

class MainViewModel(
    private val repositoryLocal: CharactersRepositoryLocal
) : ViewModel() {

    private val characterUseCase: CharacterUseCase = CharacterUseCase()

    private val _showError = MutableLiveData<String>()
    val showError : LiveData<String>
        get() = _showError

    val characterList = MutableLiveData<List<Characters>>()

    val allCharactersLocalList: LiveData<List<PersonajeEntity>> = repositoryLocal.allCharactersLocalList.asLiveData()

    fun insertPersonajeFavorito(personajeEntity: PersonajeEntity) =
        viewModelScope.launch {
            repositoryLocal.insertPersonajeFavorito(personajeEntity)
        }

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

    class MainViewModelFactory(val repository: CharactersRepositoryLocal): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw  java.lang.IllegalArgumentException("Clase ViewModel desconocida")
        }
    }
}