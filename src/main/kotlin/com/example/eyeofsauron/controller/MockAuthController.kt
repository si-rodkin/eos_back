package com.example.eyeofsauron.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Временная заглушка для системы авторизации фронтального приложения
 */
@RestController
class MockAuthController {
    @PostMapping("/api/rest-auth/login")
    fun auth() = mapOf("key" to "12345678980")
}