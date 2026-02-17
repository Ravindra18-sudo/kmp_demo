package com.example.kmpcalculator

interface Platform {
    val name: String
    val isApp: Boolean
}

expect fun getPlatform(): Platform
