package com.example.gameslibrary.data.network

import com.example.gameslibrary.data.local.Constants.API_KEY
import com.example.gameslibrary.data.model.GenresDto
import com.example.gameslibrary.util.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("genres")
    suspend fun genres(
        @Query("key") key : String = API_KEY
    ): Response<BaseResponse<List<GenresDto>>>
}