package com.example.pokedex.utils.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ExecutionThreadImpl : ExecutionThread {

    override val ioThread: CoroutineDispatcher
        get() = Dispatchers.IO
    override val mainThread: CoroutineDispatcher
        get() = Dispatchers.Main
    override val unconfinedThread: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}