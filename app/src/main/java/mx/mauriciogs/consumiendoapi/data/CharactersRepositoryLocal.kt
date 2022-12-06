package mx.mauriciogs.consumiendoapi.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import mx.mauriciogs.consumiendoapi.data.room.db.PresonajesFavDao
import mx.mauriciogs.consumiendoapi.data.room.entity.PersonajeEntity

class CharactersRepositoryLocal(
    private val personajesDao : PresonajesFavDao
) {
    @WorkerThread
    suspend fun insertPersonajeFavorito(
        personajeEntity: PersonajeEntity) {
        personajesDao.insertPersonaje(personajeEntity)
    }

    // Read
    val allCharactersLocalList: Flow<List<PersonajeEntity>> = personajesDao.getAllFavorites()
}