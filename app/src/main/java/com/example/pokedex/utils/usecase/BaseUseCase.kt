package com.example.pokedex.utils.usecase

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}