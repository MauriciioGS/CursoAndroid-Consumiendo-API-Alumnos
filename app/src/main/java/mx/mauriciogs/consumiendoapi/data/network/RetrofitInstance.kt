package mx.mauriciogs.consumiendoapi.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(
                GsonConverterFactory.create())
            .build()
    }

    val retrofitService: ApiClient by lazy {
        retrofit.create(
            ApiClient::class.java)
    }
}