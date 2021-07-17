package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.User
import com.example.eyeofsauron.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с пользователями (сотрудниками) системы
 */
@RestController
@RequestMapping(UserController.uri)
class UserController(private val service: UserService) {
    @GetMapping
    fun getAllUsers(@RequestParam("uid", required = false) uid: Long?) = service.getAllUsers(uid)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = service.getUserById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User) = service.createUser(user)

    @PutMapping
    fun updateUser(@RequestBody user: User) = service.updateUser(user)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) = service.deleteUserById(id)

    companion object {
        const val uri = "/api/users"
    }
}