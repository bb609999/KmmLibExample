package com.example.kmmlibexample

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val httpClient: HttpClient = httpClient()

}

actual fun getPlatform(): Platform = IOSPlatform()
actual fun httpClient(config: HttpClientConfig<*>.()-> Unit)= HttpClient(Darwin){
    config(this)
    engine{
        configureRequest{
            setAllowsCellularAccess(true)
        }
    }
    install(ContentNegotiation) {
        json(Json {
            this.isLenient = true
            this.ignoreUnknownKeys = true
        })
    }
}