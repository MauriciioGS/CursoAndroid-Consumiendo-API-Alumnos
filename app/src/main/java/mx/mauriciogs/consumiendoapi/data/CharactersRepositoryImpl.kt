package mx.mauriciogs.consumiendoapi.data

import mx.mauriciogs.consumiendoapi.data.model.toListCharacters
import mx.mauriciogs.consumiendoapi.data.network.ResultState
import mx.mauriciogs.consumiendoapi.domain.model.Characters
import mx.mauriciogs.consumiendoapi.data.network.RetrofitInstance
import mx.mauriciogs.consumiendoapi.domain.repository.CharacterRepository

class CharactersRepositoryImpl: CharacterRepository {

    private val retrofitService = RetrofitInstance.retrofitService

    // Implementación de la función definida en la capa de dominio: CharacterRepository
    // Esta obtiene una lista de personajes llamando al servicio de api y mapea los datos
    // obtenidos a los datos que se utilizarán en la capa de vista y que fueron definidos en
    // la capa de dominio. En caso de algun fallo en la obtención de los datos devuelve un
    // mensaje de error
    override suspend fun getAllCharacters(page: Int): ResultState<List<Characters>> {
        val result = try {
            val response = retrofitService.getAllCharacters(page).toListCharacters()
            response
        } catch (e: Exception) {
            println(e.printStackTrace())
            null
        }
        if (result != null) {
            return ResultState.Success(result)
        }
        return ResultState.Error("Ocurrió un error al hacer la petición")
    }

    // Agregar función que utilize el servicio de ApiClient para obtener sólo un personaje
    // por ID y que retorne el personaje obtenido o un error en caso de fallo

}
