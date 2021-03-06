package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.service.MarkerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с маркерами {@link Marker}
 */
@RestController
@RequestMapping(MarkerController.uri)
class MarkerController(private val service: MarkerService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @GetMapping("/free-or/{id}")
    fun getFreeOrRouteMarkers(@PathVariable id: Long): List<Marker> = service.getFreeOrRouteMarkers(id)

    @PutMapping
    fun update(@RequestBody marker: Marker) = service.update(marker)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/markers"
    }
}
