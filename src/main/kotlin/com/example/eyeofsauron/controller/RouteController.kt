package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Route
import com.example.eyeofsauron.service.PermissionService
import com.example.eyeofsauron.service.RouteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Контроллер для работы с маршрутами на охраняемых объектах
 */
@RestController
@RequestMapping(RouteController.uri)
class RouteController(
    private val service: RouteService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/by-secured-facility/{securedFacilityId}")
    fun getByObject(@PathVariable securedFacilityId: Long) = service.getBySecuredFacilityId(securedFacilityId)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Optional<Route>? {
        if (permission.hasAccess(id, service.getById(id).get()))
            return service.getById(id)
        return null
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody route: Route) {
        val id: Long = route.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.create(route)
    }

    @PutMapping
    fun update(@RequestBody route: Route) {
        val id: Long = route.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.update(route)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        if (permission.hasAccess(id, service.getById(id).get()))
            service.deleteById(id)
    }

    companion object {
        const val uri = "/api/routes"
    }
}