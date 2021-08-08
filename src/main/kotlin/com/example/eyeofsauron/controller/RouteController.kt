package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Route
import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.PermissionService
import com.example.eyeofsauron.service.RouteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.AccessControlException
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
    fun getAll(@RequestHeader authorization: String): List<Route> =
        service.getAll().filter { item -> permission.hasAccess(item, authorization) }

    @GetMapping("/by-secured-facility/{securedFacilityId}")
    fun getByObject(@PathVariable securedFacilityId: Long, @RequestHeader authorization: String): List<Route> =
        service.getBySecuredFacilityId(securedFacilityId).filter { item -> permission.hasAccess(item, authorization) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, @RequestHeader authorization: String): Optional<Route>? {
        if (permission.hasAccess(service.getById(id).get(), authorization))
            return service.getById(id)
        throw AccessControlException("Unable to get element")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody route: Route) = service.create(route)

    @PutMapping
    fun update(@RequestBody route: Route, @RequestHeader authorization: String) {
        if (permission.hasAccess(route, authorization))
            service.update(route)
        else throw AccessControlException("Unable to edit element")
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long, @RequestHeader authorization: String) {
        if (permission.hasAccess(service.getById(id).get(), authorization))
            service.deleteById(id)
        else throw AccessControlException("Unable to delete element")
    }

    companion object {
        const val uri = "/api/routes"
    }
}