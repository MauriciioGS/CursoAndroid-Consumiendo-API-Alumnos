package mx.mauriciogs.consumiendoapi.application

import android.app.Application
import mx.mauriciogs.consumiendoapi.data.CharactersRepositoryLocal
import mx.mauriciogs.consumiendoapi.data.room.db.PresonajesFavRoom

class PersonajesApplication: Application() {
    private val database by lazy {
        PresonajesFavRoom.getDatabase(this@PersonajesApplication)
    }

    val repository by lazy { CharactersRepositoryLocal(database.personajesFavDao()) }
}