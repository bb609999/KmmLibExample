package com.example.kmmlibexample

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override val httpClient: HttpClient = httpClient()
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun httpClient(config: HttpClientConfig<*>.()-> Unit) = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            this.isLenient = true
            this.ignoreUnknownKeys = true
        })
    }
}