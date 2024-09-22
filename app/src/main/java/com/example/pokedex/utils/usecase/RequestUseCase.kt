package com.example.pokedex.utils.usecase

import com.example.pokedex.utils.sealed.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class RequestUseCase<P, T>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    protected abstract suspend fun executeOnBackground(params: P? = null): T

    suspend fun execute(parameters: P? = null) = flow<Result<T>> {
        emit(Result.OnLoading())
        runCatching {
            executeOnBackground(parameters)
        }.onSuccess {
            emit(Result.OnSuccess(it))
        }.onFailure {
            emit(Result.OnError(it))
        }
    }.flowOn(dispatcher)
}