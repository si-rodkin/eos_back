package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.service.RouteBypassService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RouteBypassController.uri)
class RouteBypassController(private val service: RouteBypassService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody routeBypass: RouteBypass) = service.create(routeBypass)

    @PutMapping("/{id}")
    fun update(@RequestBody routeBypass: RouteBypass) = service.update(routeBypass)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/route-bypasses"
    }
}