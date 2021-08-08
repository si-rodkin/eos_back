package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.MarkerService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.AccessControlException
import java.util.*

/**
 * Контроллер для работы с маркерами {@link Marker}
 */
@RestController
@RequestMapping(MarkerController.uri)
class MarkerController(private val service: MarkerService) {
    @GetMapping
    fun getAll(): List<Marker> = service.getAll()

    @GetMapping("/free-or/{id}")
    fun getFreeOrRouteMarkers(@PathVariable id: Long): List<Marker> = service.getFreeOrRouteMarkers(id)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PutMapping
    fun update(@RequestBody marker: Marker) = service.update(marker)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/markers"
    }
}
