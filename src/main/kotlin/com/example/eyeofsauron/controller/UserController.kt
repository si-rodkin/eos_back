package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.User
import com.example.eyeofsauron.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с пользователями (сотрудниками) системы
 */
@RestController
@RequestMapping(UserController.uri)
class UserController(private val service: UserService) {
    @GetMapping
    fun getAllUsers(@RequestParam("uid", required = false) uid: Long?) = service.getAll(uid)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: User) = service.create(user)

    @PutMapping
    fun update(@RequestBody user: User) = service.update(user)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/users"
    }
}