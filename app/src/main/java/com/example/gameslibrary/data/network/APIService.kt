package com.example.gameslibrary.data.network

import com.example.gameslibrary.data.local.Constants.API_KEY
import com.example.gameslibrary.data.model.details.DetailsDto
import com.example.gameslibrary.data.model.games.GamesDto
import com.example.gameslibrary.data.model.genres.GenresDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("genres")
    suspend fun genres(
        @Query("key") key: String = API_KEY
    ): GenresDto

    @GET("games")
    suspend fun gamesByGenre(
        @Query("key") key: String = API_KEY,
        @Query("page_size") pageSize: Int = 30,
        @Query("genres") genre: String
    ): GamesDto



    @GET("games/{id}")
    suspend fun gameDetails(
        @Path("id") gameId: String,
        @Query("key") key: String = API_KEY
    ): DetailsDto
}