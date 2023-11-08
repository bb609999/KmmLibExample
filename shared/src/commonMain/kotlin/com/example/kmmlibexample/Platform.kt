package com.example.kmmlibexample

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

interface Platform {
    val name: String
    val httpClient: HttpClient
}

expect fun getPlatform(): Platform
expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient