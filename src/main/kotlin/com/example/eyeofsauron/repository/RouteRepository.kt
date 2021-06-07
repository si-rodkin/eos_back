package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface RouteRepository : JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
    fun findByGuardedObjectId(id: Long): List<Route>
}