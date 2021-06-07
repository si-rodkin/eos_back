package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.service.MarkerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с маркерами {@link Marker}
 * @author rodkinsi
 */
@RestController
@RequestMapping(MarkerController.uri)
class MarkerController(private val service: MarkerService) {
    @GetMapping
    fun getAllMarkers() = service.getAllMarkers()

    @GetMapping("/{id}")
    fun getMarker(@PathVariable id: Long) = service.getMarkerById(id)

    @GetMapping("/free-or/{id}")
    fun getFreeOrRouteMarkers(@PathVariable id: Long): List<Marker> = service.getFreeOrRouteMarkers(id)

    @PostMapping
    fun createMarker(@RequestBody marker: Marker) = service.createMarker(marker)

    @PutMapping("/{id}")
    fun updateMarker(@RequestBody marker: Marker) = service.updateMarker(marker)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMarker(@PathVariable id: Long) = service.deleteMarkerById(id)

    companion object {
        const val uri = "/api/markers"
    }
}