package mx.mauriciogs.consumiendoapi.domain

import mx.mauriciogs.consumiendoapi.data.CharactersRepositoryImpl

class CharacterUseCase {
    private val charactersRepositoryImpl = CharactersRepositoryImpl()

    suspend fun getAllCharacter(page: Int) = charactersRepositoryImpl.getAllCharacters(page)

    // Definir la función que obtiene un personaje por id desde CharactersRepositoryImpl
}