package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.PermissionService
import com.example.eyeofsauron.service.RouteBypassService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.AccessControlException
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
    fun getAll(@RequestHeader authorization: String): List<RouteBypass> =
        service.getAll()
            .filter { item ->
                permission.hasAccess(item.id, service.getById(item.id).get(), authorization)
            }

    @GetMapping("/by-route/{routeId}")
    fun getByRoute(@PathVariable routeId: Long, @RequestHeader authorization: String): List<RouteBypass> =
        service.getByRoute(routeId)
            .filter { item ->
                permission.hasAccess(item.id, service.getById(item.id).get(), authorization)
            }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, @RequestHeader authorization: String): Optional<RouteBypass>? {
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            return service.getById(id)
        throw AccessControlException("Unable to get element")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody routeBypass: RouteBypass) = service.create(routeBypass)


    @PutMapping
    fun update(@RequestBody routeBypass: RouteBypass, @RequestHeader authorization: String) {
        val id: Long = routeBypass.id
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            service.update(routeBypass)
        else throw AccessControlException("Unable to edit element")
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long, @RequestHeader authorization: String) {
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            service.deleteById(id)
        else throw AccessControlException("Unable to delete element")
    }

    companion object {
        const val uri = "/api/route-bypasses"
    }
}
