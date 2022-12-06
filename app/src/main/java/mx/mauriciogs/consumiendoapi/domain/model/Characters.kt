package mx.mauriciogs.consumiendoapi.domain.model

data class Characters(
    val id: Int,
    val name: String,
    val gender: String,
    val specie: String,
    val state: String,
    val image: String
)
