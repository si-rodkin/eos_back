package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Statistics
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StatisticsRepository : JpaRepository<Statistics, Long>, JpaSpecificationExecutor<Statistics>{
    @Query(nativeQuery = true, value = "SELECT * FROM statistics s WHERE s.marker_id is null OR " +
            "(SELECT marker_id FROM check_point WHERE s.check_point_id = id) = s.marker_id")
    fun findPlanned(): List<Statistics>

    @Query(nativeQuery = true, value = "SELECT * FROM statistics s WHERE " +
            "(SELECT marker_id FROM check_point WHERE s.check_point_id = id) <> s.marker_id")
    fun findUnplanned(): List<Statistics>
}