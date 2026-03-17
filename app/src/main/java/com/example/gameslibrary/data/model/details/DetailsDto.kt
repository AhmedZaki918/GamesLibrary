package com.example.gameslibrary.data.model.details

data class DetailsDto(
    val id : Int? = 0,
    val slug : String? = "",
    val name : String? = "",
    val description : String? = "",
    val rating : Double? = 0.0,
    val background_image : String? = "",
    val released : String? = ""
)
