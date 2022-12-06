package mx.mauriciogs.consumiendoapi.data.room.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mx.mauriciogs.consumiendoapi.data.room.db.dbConstants.DATABASE_PFAVORITES_TABLE
import mx.mauriciogs.consumiendoapi.data.room.entity.PersonajeEntity

@Dao
interface PresonajesFavDao {
    @Insert
    suspend fun insertPersonaje(personajeEntity: PersonajeEntity)

    @Query("SELECT * FROM $DATABASE_PFAVORITES_TABLE")
    fun getAllFavorites(): Flow<List<PersonajeEntity>>

    @Update
    suspend fun updatePFavorite(personaje: PersonajeEntity)

    @Delete
    suspend fun deletePFavorite(personaje: PersonajeEntity)
}

object dbConstants {
    const val DATABASE_NAME = "personajes_favoritos_room"
    const val DATABASE_PFAVORITES_TABLE = "personajes_favoritos"
}