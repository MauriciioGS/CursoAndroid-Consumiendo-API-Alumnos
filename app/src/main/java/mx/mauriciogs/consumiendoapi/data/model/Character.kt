package mx.mauriciogs.consumiendoapi.data.model

import mx.mauriciogs.consumiendoapi.domain.model.Character

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun Character.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        origin = origin,
        image = image,
        location = location,
        gender = gender
    )
}