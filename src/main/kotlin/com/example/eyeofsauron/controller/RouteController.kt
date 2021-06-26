package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Route
import com.example.eyeofsauron.service.RouteService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с маршрутами на охраняемых объектах
 * @author rodkinsi
 */
@RestController
@RequestMapping(RouteController.uri)
class RouteController(private val service: RouteService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/by-secured-facility/{securedFacilityId}")
    fun getByObject(@PathVariable securedFacilityId: Long) = service.getBySecuredFacilityId(securedFacilityId)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody route: Route) = service.create(route)

    @PutMapping
    fun update(@RequestBody route: Route) = service.update(route)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/routes"
    }
}