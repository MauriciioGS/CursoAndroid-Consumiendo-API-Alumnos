package mx.mauriciogs.consumiendoapi.domain.repository

import mx.mauriciogs.consumiendoapi.data.network.ResultState
import mx.mauriciogs.consumiendoapi.domain.model.Characters

interface CharacterRepository {
    suspend fun getAllCharacters(page: Int): ResultState<List<Characters>>
}