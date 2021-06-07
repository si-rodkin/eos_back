package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Object
import com.example.eyeofsauron.service.ObjectService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с охраняемыми объектами
 * @author rodkinsi
 */
@RestController
@RequestMapping(ObjectController.uri)
class ObjectController(private val service: ObjectService) {
    @GetMapping
    fun getAllObjects() = service.getAllObjects()

    @GetMapping("/{id}")
    fun getObject(@PathVariable id: Long) = service.getObjectById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createObject(@RequestBody obj: Object) = service.createObject(obj)

    @PutMapping("/{id}")
    fun updateObject(@RequestBody obj: Object) = service.updateObject(obj)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteObject(@PathVariable id: Long) = service.deleteObjectById(id)

    companion object {
        const val uri = "/api/guarded-objects"
    }
}