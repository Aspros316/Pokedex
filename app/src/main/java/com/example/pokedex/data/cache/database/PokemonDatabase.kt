package com.example.pokedex.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.data.cache.database.dao.PokemonDao
import com.example.pokedex.data.cache.model.PokemonDb

@Database(entities = [PokemonDb::class], version = 1)
abstract class PokemonDatabase : RoomDatabase(){
    abstract fun pokemonDao(): PokemonDao
}