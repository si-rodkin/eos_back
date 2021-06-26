package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Route
import com.example.eyeofsauron.repository.RouteRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с маршрутами на охраняемых объектах
 * @author rodkinsi
 */
@Service
class RouteService(private val repository: RouteRepository) {
    fun getAll(): List<Route> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun getBySecuredFacilityId(securedFacilityId: Long) = repository.findBySecuredFacilityId(securedFacilityId)

    fun create(route: Route) = repository.save(route)

    fun update(route: Route) {
        if(!repository.existsById(route.id))
            throw(Exception("Record not found!"))
        repository.save(route)
    }

    fun delete(route: Route) = repository.delete(route)

    fun deleteById(id: Long) = repository.deleteById(id)
}