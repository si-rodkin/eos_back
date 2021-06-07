package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Route
import com.example.eyeofsauron.service.RouteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с маршрутами на охраняемых объектах
 * @author rodkinsi
 */
@RestController
class RouteController(private val service: RouteService) {
    @GetMapping("/api/guarded-objects/{objectId}/guard-routes")
    fun getAllObjectRoutes(@PathVariable objectId: Long) = service.getObjectRoutes(objectId)

    @GetMapping("/api/guard-routes/{id}")
    fun getRoute(@PathVariable id: Long) = service.getRouteById(id)

    @PostMapping("/api/guard-routes")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRoute(@RequestBody route: Route) = service.createRoute(route)

    @PutMapping("/api/guard-routes/{id}")
    fun updateRoute(@RequestBody route: Route) = service.updateRoute(route)

    @DeleteMapping("/api/guard-routes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRoute(@PathVariable id: Long) = service.deleteRouteById(id)
}