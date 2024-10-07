package com.example.pokedex.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.data.repository.graphql.PokeApi
import com.example.pokedex.domain.list.mapper.toDomain
import com.example.pokedex.domain.model.Pokemon
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingDataSource(private val pokeApi: PokeApi) :
    PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Pokemon> {
        return try {
                    val position = params.key ?: 0
                    val pageSize = params.loadSize
                    val response = pokeApi.getListPokemon(position, pageSize)
                    Log.d("Pagination event", "Offset = $position Limit = ${pageSize}")
                    val data = response

            LoadResult.Page(
                data = data?.remotePokemonTypes?.toDomain() ?: listOf(),
                prevKey = if (position < pageSize) null else (position - pageSize),
                nextKey = if (position >= 1302) null else (position + pageSize)
            )

                } catch (exception: IOException) {
                    return LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    return LoadResult.Error(exception)
                }
            }
}