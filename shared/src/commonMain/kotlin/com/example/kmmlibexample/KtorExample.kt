package com.example.kmmlibexample

import com.example.kmmlibexample.model.ApiResponse
import com.example.kmmlibexample.model.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.contentnegotiation.JsonContentTypeMatcher
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.websocket.websocketServerAccept
import io.ktor.serialization.kotlinx.json.json

class KtorExample {
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getAllCharacters(): List<Character> {
        val response = client.get("https://rickandmortyapi.com/api/character")
        return response.body<ApiResponse>().results
    }
}