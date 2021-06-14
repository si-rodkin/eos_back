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
    fun getAll(): List<Marker> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun getFreeOrRouteMarkers(routeId: Long) = repository.findByRouteIdOrFree(routeId)

    fun getByRfid(rfid: String) = repository.findByRfid(rfid)

    fun create(marker: Marker) = repository.save(marker)

    fun update(marker: Marker) = repository.save(marker)

    fun delete(marker: Marker) = repository.delete(marker)

    fun deleteById(id: Long) = repository.deleteById(id)
}