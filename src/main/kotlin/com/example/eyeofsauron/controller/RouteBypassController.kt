package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.service.PermissionService
import com.example.eyeofsauron.service.RouteBypassService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Контроллер для работы с обходами маршрутов
 */
@RestController
@RequestMapping(RouteBypassController.uri)
class RouteBypassController(
    private val service: RouteBypassService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Optional<RouteBypass>? {
        if (permission.hasAccess(id, service.getById(id).get()))
            return service.getById(id)
        return null
    }

    @GetMapping("/by-route/{routeId}")
    fun getByRoute(@PathVariable routeId: Long) = service.getByRoute(routeId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody routeBypass: RouteBypass) {
        val id: Long = routeBypass.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.create(routeBypass)
    }

    @PutMapping
    fun update(@RequestBody routeBypass: RouteBypass) {
        val id: Long = routeBypass.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.update(routeBypass)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        if (permission.hasAccess(id, service.getById(id).get()))
            service.deleteById(id)
    }

    companion object {
        const val uri = "/api/route-bypasses"
    }
}
