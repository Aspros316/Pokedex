package com.example.pokedex.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.data.cache.database.config.POKEMON_TABLE

@Entity(tableName = POKEMON_TABLE)
data class PokemonTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val pokemonName: String,
    @ColumnInfo(name = "favorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "image")
    val image: String,
    )
