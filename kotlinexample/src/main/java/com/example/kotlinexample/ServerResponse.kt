package com.example.kotlinexample

fun Int.isEven(): Boolean {
    return this % 2 == 0
}

sealed class ServerResponse {

    class Success(data: String) : ServerResponse()

    class Error(err: Throwable) : ServerResponse()

}

fun onServerRespose(
        response: ServerResponse,
        onSucces: (ServerResponse.Success) -> Unit,
        onError: (ServerResponse.Error) -> Unit
) {
    when(response) {
        is ServerResponse.Success -> onSucces(response)
        is ServerResponse.Error -> onError(response)
    }
}

val onSucces: (ServerResponse.Success) -> Unit = {}
val onError: (ServerResponse.Error) -> Unit = {}