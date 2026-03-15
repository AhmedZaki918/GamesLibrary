package com.example.gameslibrary.util

data class BaseResponse<T>(
    val data : T? = null,
    val message: String? = null
)