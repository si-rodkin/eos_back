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
@RequestMapping(RouteController.uri)
class RouteController(private val service: RouteService) {
    @GetMapping("/by-secured-facility/{securedFacilityId}")
    fun getAll(@PathVariable securedFacilityId: Long) = service.get(securedFacilityId)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody route: Route) = service.create(route)

    @PutMapping("/{id}")
    fun update(@RequestBody route: Route) = service.update(route)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/routes"
    }
}