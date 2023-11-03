package com.example.kmmlibexample

import com.example.kmmlibexample.model.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorExample {
    private val client = HttpClient()

    suspend fun getAllCharacters(): List<Character> {
        val response = client.get("https://rickandmortyapi.com/api/character")
        return response.body()
    }
}