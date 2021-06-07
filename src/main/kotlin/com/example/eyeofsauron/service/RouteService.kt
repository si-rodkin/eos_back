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
    fun getAllRoutes(): List<Route> = repository.findAll()

    fun getRouteById(id: Long) = repository.findById(id)

    fun getObjectRoutes(objectId: Long) = repository.findByGuardedObjectId(objectId)

    fun createRoute(route: Route) = repository.save(route)

    fun updateRoute(route: Route) = repository.save(route)

    fun delete(route: Route) = repository.delete(route)

    fun deleteRouteById(id: Long) = repository.deleteById(id)
}