package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.service.MarkerService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Контроллер для работы с маркерами {@link Marker}
 */
@RestController
@RequestMapping(MarkerController.uri)
class MarkerController(
    private val service: MarkerService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Optional<Marker>? {
        if (permission.hasAccess(id, service.getById(id).get()))
            return service.getById(id)
        return null
    }

    @GetMapping("/free-or/{id}")
    fun getFreeOrRouteMarkers(@PathVariable id: Long): List<Marker> = service.getFreeOrRouteMarkers(id)

    @PutMapping
    fun update(@RequestBody marker: Marker) {
        val id: Long? = marker.id
        if (id?.let { permission.hasAccess(it, service.getById(id).get()) } == true)
            service.create(marker)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        if (permission.hasAccess(id, service.getById(id).get()))
            service.deleteById(id)
    }

    companion object {
        const val uri = "/api/markers"
    }
}
