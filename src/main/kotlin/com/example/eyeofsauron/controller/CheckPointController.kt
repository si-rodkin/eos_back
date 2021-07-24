package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.service.CheckPointService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Контроллер для работы с контрольными точками
 */
@RestController
@RequestMapping(CheckPointController.uri)
class CheckPointController(
    private val service: CheckPointService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Optional<CheckPoint>? {
        if (permission.hasAccess(id, service.getById(id).get()))
            return service.getById(id)
        return null
    }

    @GetMapping("/by-routebypass/{id}")
    fun getByBypass(@PathVariable id: Long) = service.getByBypass(listOf(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody checkPoint: CheckPoint) {
        val id: Long = checkPoint.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.create(checkPoint)
    }

    @PutMapping
    fun update(@RequestBody checkPoint: CheckPoint) {
        val id: Long = checkPoint.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.update(checkPoint)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        if (permission.hasAccess(id, service.getById(id).get()))
            service.deleteById(id)
    }

    companion object {
        const val uri = "/api/check-points"
    }
}