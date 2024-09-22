package com.example.pokedex.utils.network

import kotlinx.coroutines.CoroutineDispatcher

interface ExecutionThread {
    val ioThread: CoroutineDispatcher
    val mainThread: CoroutineDispatcher
    val unconfinedThread: CoroutineDispatcher
}