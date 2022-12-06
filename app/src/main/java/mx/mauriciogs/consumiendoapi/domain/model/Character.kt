package mx.mauriciogs.consumiendoapi.domain.model

import mx.mauriciogs.consumiendoapi.data.model.Location
import mx.mauriciogs.consumiendoapi.data.model.Origin

data class Character (
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String
    )