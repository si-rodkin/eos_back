package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.repository.MarkerRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с маркерами
 * @author rodkinsi
 */
@Service
class MarkerService(private val repository: MarkerRepository) {
    fun getAllMarkers(): List<Marker> = repository.findAll()

    fun getMarkerById(id: Long) = repository.findById(id)

    fun getFreeOrRouteMarkers(routeId: Long) = repository.findByRouteIdOrFree(routeId)

    fun createMarker(marker: Marker) = repository.save(marker)

    fun updateMarker(marker: Marker) = repository.save(marker)

    fun delete(marker: Marker) = repository.delete(marker)

    fun deleteMarkerById(id: Long) = repository.deleteById(id)
}