package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface RouteRepository : JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
    fun findBySecuredFacilityId(id: Long): List<Route>
}