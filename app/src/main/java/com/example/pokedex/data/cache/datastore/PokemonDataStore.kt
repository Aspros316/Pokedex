package com.example.pokedex.data.cache.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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