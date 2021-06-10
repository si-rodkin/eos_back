package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Marker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MarkerRepository : JpaRepository<Marker, Long>, JpaSpecificationExecutor<Marker> {
    @Query(nativeQuery = true, value = "SELECT * FROM marker WHERE (:id > -1 AND route_id = :id) OR route_id is null")
    fun findByRouteIdOrFree(id: Long): List<Marker>
}