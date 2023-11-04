package com.example.kmmlibexample.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String
)

@Serializable
data class Location(
    val name: String,
)