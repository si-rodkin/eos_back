package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Employee
import com.example.eyeofsauron.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с пользователями (сотрудниками) системы
 */
@RestController
@RequestMapping(EmployeeController.uri)
class EmployeeController(private val service: EmployeeService) {
    @GetMapping
    fun getAllEmployees(@RequestParam("uid", required = false) uid: Long?) = service.getAll(uid)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody employee: Employee) = service.create(employee)

    @PutMapping
    fun update(@RequestBody employee: Employee) = service.update(employee)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/employees"
    }
}