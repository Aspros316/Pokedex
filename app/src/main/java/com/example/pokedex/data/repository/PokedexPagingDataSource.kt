package com.example.pokedex.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.data.repository.retrofit.PokedexWebService
import com.example.pokedex.domain.list.mapper.toDomain
import com.example.pokedex.domain.model.Pokemon

class PokedexPagingDataSource(private val repository: PokedexWebService) :
    PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Pokemon> = try {
        val position = params.key ?: 0
        val pageSize = params.loadSize
        val response = repository.getAllPokemon(position, pageSize)
        Log.d("Pagination event", "Offset = $position Limit = ${pageSize}")
        val error = response.errorBody()
        val data = response.body()

        if (error != null) {
            LoadResult.Error(throw Exception("No Response"))
        } else if (data != null) {
            LoadResult.Page(
                data = data.remotePokemonTypes.toDomain(),
                prevKey = if (position < pageSize) null else (position - pageSize),
                nextKey = if (position >= 1302) null else (position + pageSize)
            )
        } else {
            LoadResult.Error(throw Exception("No Response"))
        }
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}