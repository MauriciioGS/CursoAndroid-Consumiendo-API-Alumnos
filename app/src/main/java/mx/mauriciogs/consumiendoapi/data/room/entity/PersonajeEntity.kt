package mx.mauriciogs.consumiendoapi.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personajes_favoritos")
data class PersonajeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "personajeId") var id: Long = 0 ,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "especie") var especie: String
)
