package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.service.CheckPointService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с контрольными точками
 */
@RestController
@RequestMapping(CheckPointController.uri)
class CheckPointController(private val service: CheckPointService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @GetMapping("/by-routebypass/{id}")
    fun getByBypass(@PathVariable id: Long) = service.getByBypass(listOf(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody checkPoint: CheckPoint) = service.create(checkPoint)

    @PutMapping
    fun update(@RequestBody checkPoint: CheckPoint) = service.update(checkPoint)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/check-points"
    }
}