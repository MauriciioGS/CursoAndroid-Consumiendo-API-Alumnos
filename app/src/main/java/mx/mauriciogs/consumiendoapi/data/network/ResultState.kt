package mx.mauriciogs.consumiendoapi.data.network

sealed class ResultState<out T> {

    data class Success<out T>(val data: T): ResultState<T>()
    data class Error<T>(val message: String?): ResultState<T>()

}
