package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.service.CheckPointService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с контрольными точками
 * @author rodkinsi
 */
@RestController
@RequestMapping(CheckPointController.uri)
class CheckPointController(private val service: CheckPointService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody checkPoint: CheckPoint) = service.create(checkPoint)

    @PutMapping("/{id}")
    fun update(@RequestBody checkPoint: CheckPoint) = service.update(checkPoint)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/check-points"
    }
}