package com.example.pokedex.data.cache.datastore.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

class PokemonDataStoreBuilder @Inject constructor(context: Context) {

    companion object {
        const val NAME = "PokemonDataStore"

        val Context.datastore: DataStore<Preferences> by preferencesDataStore(
            name = NAME
        )
    }

    val getDataStore = context.datastore
}