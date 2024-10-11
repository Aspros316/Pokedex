package com.example.pokedex.data.cache.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.pokedex.data.cache.datastore.config.PokemonDataStoreBuilder
import com.example.pokedex.ui.model.SignUpCredentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonDataStore @Inject constructor(
    private val pokemonDataStoreBuilder: PokemonDataStoreBuilder
) {

    private val usernameKey = stringPreferencesKey(USERNAME)
    private val userPasswordKey = stringPreferencesKey(PASSWORD)
    private val isValidCredentialsKey = booleanPreferencesKey(name = IS_VALID_CREDENTIALS)
    private val  useTimeKey = longPreferencesKey(name ="usage_time")
    private val pokemonSeen = intPreferencesKey(name ="pokemon_vistos")


    suspend fun savePokemonSeen(newSeen: Int) {
        with(pokemonDataStoreBuilder) {
            getDataStore.edit { preferences ->
                val seen = preferences[pokemonSeen] ?: 0
                preferences[pokemonSeen] = seen + newSeen
            }
        }
    }

    fun getPokemonSeen(): Flow<Int> =
        with(pokemonDataStoreBuilder) {
            getDataStore.data.map { preferences ->
                preferences[pokemonSeen] ?: 0
            }
        }

    fun getUseTime(): Flow<Long> =
        with(pokemonDataStoreBuilder) {
            getDataStore.data.map { preferences ->
                preferences[useTimeKey] ?: 0
            }
        }

    suspend fun saveUseTime(newTime: Long) {
        with(pokemonDataStoreBuilder) {
            getDataStore.edit { preferences ->
                val currentTime = preferences[useTimeKey] ?: 0L
                println("newTime ${newTime}")
                println("currentTime ${currentTime}")
                preferences[useTimeKey] = newTime + currentTime
            }
        }
    }

    suspend fun saveSignUp(name: String, password: String, isValid: Boolean) {
        with(pokemonDataStoreBuilder) {
            getDataStore.edit { preferences ->
                preferences[usernameKey] = name
                preferences[userPasswordKey] = password
                preferences[isValidCredentialsKey] = isValid
            }
        }
    }

    fun getSignUp(): Flow<SignUpCredentials> = with(pokemonDataStoreBuilder) {
        getDataStore.data.map {
            SignUpCredentials(
                username = it[usernameKey] ?: "",
                password = it[userPasswordKey] ?: "",
                isValid = it[isValidCredentialsKey] ?: false
            )
        }
    }

    suspend fun clearDatastore() {
        with(pokemonDataStoreBuilder) {
            getDataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }

}

const val USERNAME = "username"
const val PASSWORD = "password"
const val IS_VALID_CREDENTIALS = "is_valid_credential"