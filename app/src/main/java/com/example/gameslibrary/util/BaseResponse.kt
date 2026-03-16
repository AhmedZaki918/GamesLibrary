package com.example.gameslibrary.util

import com.squareup.moshi.Json

data class BaseResponse<T>(
    @param:Json(name = "results") val results: T? = null,
    val message: String? = null
)