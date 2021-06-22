package com.example.eyeofsauron.controller

import com.example.eyeofsauron.config.JwtProvider
import com.example.eyeofsauron.model.auth.Request
import com.example.eyeofsauron.model.auth.Response
import com.example.eyeofsauron.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
class AuthController(private val jwtProvider: JwtProvider,
private val userService: UserService) {
    @PostMapping("/auth/login")
    fun auth(@RequestBody creds: Request) =
        userService.getByUsernameAndPassword(creds.username, creds.password)?.run {
            Response(jwtProvider.generateToken(this.username))
        } ?: throw RuntimeException()
}