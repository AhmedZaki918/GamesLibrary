package com.example.gameslibrary.data.network

import com.example.gameslibrary.data.local.Constants.API_KEY
import com.example.gameslibrary.data.model.games.GamesDto
import com.example.gameslibrary.data.model.genres.GenresDto
import com.example.gameslibrary.util.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("genres")
    suspend fun genres(
        @Query("key") key : String = API_KEY
    ): Response<BaseResponse<List<GenresDto>>>

    @GET("games")
    suspend fun gamesByGenre(
        @Query("key") key : String = API_KEY,
        @Query("page_size") pageSize : Int = 30,
        @Query("genres") genre : String
    ): Response<BaseResponse<List<GamesDto>>>
}