package mx.mauriciogs.consumiendoapi.data.model

import mx.mauriciogs.consumiendoapi.domain.model.Characters

data class Characters(
    val info: Info,
    val results: List<Character>
)

fun mx.mauriciogs.consumiendoapi.data.model.Characters.toListCharacters(): List<Characters> {
    val resultEntries = results.mapIndexed { _, entries ->
        Characters(
            id = entries.id,
            name = entries.name,
            specie = entries.species,
            image = entries.image,
            state = entries.status,
            gender = entries.gender
        )
    }
    return resultEntries
}