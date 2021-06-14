package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.RouteBypass
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface RouteBypassRepository : JpaRepository<RouteBypass, Long>, JpaSpecificationExecutor<RouteBypass> {
//    fun findBy
}
