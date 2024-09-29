package com.example.pokedex.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.cache.database.config.POKEMON_TABLE
import com.example.pokedex.data.cache.model.PokemonTable
import kotlinx.coroutines.flow.Flow


@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemonTable: PokemonTable)

    @Query("SELECT * FROM '$POKEMON_TABLE' WHERE id = :pokemonId")
    fun getPokemon(pokemonId: Int): Flow<PokemonTable>

    @Query("DELETE FROM '$POKEMON_TABLE' WHERE id = :pokemonId")
    fun deletePokemon(pokemonId: Int)

    @Query("SELECT * FROM '$POKEMON_TABLE'")
    fun getAllFavorite(): Flow<List<PokemonTable>>

}