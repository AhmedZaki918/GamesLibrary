package com.example.gameslibrary.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gameslibrary.data.model.games.GamesResults
import com.example.gameslibrary.data.network.APIService

class GamesPagingSource(
    private val api: APIService,
    val genre: String
) : PagingSource<Int, GamesResults>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GamesResults> {
        return try {
            val page = params.key ?: 1
            val response = api.gamesByGenre(
                page = page,
                genre = genre
            )

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GamesResults>): Int? {
        return state.anchorPosition
    }
}