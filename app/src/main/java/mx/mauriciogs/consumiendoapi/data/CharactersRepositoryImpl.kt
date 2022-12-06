package mx.mauriciogs.consumiendoapi.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.mauriciogs.consumiendoapi.data.model.toListCharacters
import mx.mauriciogs.consumiendoapi.data.network.ResultState
import mx.mauriciogs.consumiendoapi.domain.model.Characters
import mx.mauriciogs.consumiendoapi.data.network.RetrofitInstance
import mx.mauriciogs.consumiendoapi.domain.repository.CharacterRepository

class CharactersRepositoryImpl: CharacterRepository {

    private val retrofitService = RetrofitInstance.retrofitService

    override suspend fun getAllCharacters(page: Int): ResultState<List<Characters>> {
        val result = withContext(Dispatchers.Default) {
            try {
                val response = retrofitService.getAllCharacters(page).toListCharacters()
                response
            } catch (e: Exception) {
                println(e.printStackTrace())
                return@withContext null
            }
        }
        if (result != null) {
            return ResultState.Success(result)
        }
        return ResultState.Error("Ocurrió un error al hacer la petición")
    }

}
