package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.repository.RouteBypassRepository
import org.springframework.stereotype.Service

@Service
class RouteBypassService(private val repository: RouteBypassRepository) {
    fun getAll(): List<RouteBypass> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun create(routeBypassBypass: RouteBypass) = repository.save(routeBypassBypass)

    fun update(routeBypassBypass: RouteBypass) = repository.save(routeBypassBypass)

    fun delete(routeBypassBypass: RouteBypass) = repository.delete(routeBypassBypass)

    fun deleteById(id: Long) = repository.deleteById(id)
}