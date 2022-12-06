package mx.mauriciogs.consumiendoapi.data.network

import mx.mauriciogs.consumiendoapi.data.model.Characters
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("character/")
    suspend fun getAllCharacters( @Query("page") page: Int): Characters
}