package com.example.pokedex.data.repository.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.example.pokedex.data.repository.model.RemotePokemon
import com.example.rocketreserver.TypesPokemonQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.pokedex.data.mapper.toRemote
import com.example.pokedex.data.repository.model.RemotePokemonTypes
import javax.inject.Inject


class PokeApi @Inject constructor(
    private val apolloClient: ApolloClient
) {

   suspend fun getListPokemon(offset: Int, limit: Int): RemotePokemonTypes? {
        return apolloClient
            .query(TypesPokemonQuery(Optional.present(offset), Optional.present(limit)))
            .execute()
            .data?.pokemons?.toRemote()
    }
}
