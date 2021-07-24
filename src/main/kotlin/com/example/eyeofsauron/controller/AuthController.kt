package com.example.eyeofsauron.controller

import com.example.eyeofsauron.config.JwtProvider
import com.example.eyeofsauron.entity.User
import com.example.eyeofsauron.model.auth.Request
import com.example.eyeofsauron.model.auth.Response
import com.example.eyeofsauron.service.UserService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException


@RestController
class AuthController(
    private val jwtProvider: JwtProvider,
    private val userService: UserService
) {
    @PostMapping("/auth/login")
    fun auth(@RequestBody creds: Request) =
        userService.getByUsernameAndPassword(creds.username, creds.password)?.run {
            val token: String = jwtProvider.generateToken(this.username)
            PermissionService.currentEmployeeUsername = jwtProvider.getLoginFromToken(token).toString()
            print(PermissionService.currentEmployeeUsername)
            Response(token)
        } ?: throw RuntimeException()

    @GetMapping("/auth/user")
    fun getCurrentUser(@RequestHeader authorization: String) =
        userService.getByUsername(jwtProvider.getLoginFromToken(authorization) ?: throw EntityNotFoundException())

    @PutMapping("/auth/change-password")
    fun changePassword(@RequestBody user: User) = userService.changePassword(user)
}