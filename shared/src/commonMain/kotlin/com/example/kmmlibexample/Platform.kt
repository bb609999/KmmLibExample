package com.example.kmmlibexample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform