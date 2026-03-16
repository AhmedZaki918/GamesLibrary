package com.example.gameslibrary.util

data class BaseResponse<T>(
    val results : T? = null,
    val message: String? = null
)