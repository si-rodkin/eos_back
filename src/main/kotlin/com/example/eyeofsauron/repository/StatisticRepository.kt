package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Statistic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StatisticRepository : JpaRepository<Statistic, Long>, JpaSpecificationExecutor<Statistic>{
    @Query(nativeQuery = true, value = "SELECT * FROM statistic s WHERE s.marker_id is null OR " +
            "(SELECT marker_id FROM check_point WHERE s.check_point_id = id) = s.marker_id")
    fun findPlanned(): List<Statistic>

    @Query(nativeQuery = true, value = "SELECT * FROM statistic s WHERE " +
            "(SELECT marker_id FROM check_point WHERE s.check_point_id = id) <> s.marker_id")
    fun findUnplanned(): List<Statistic>
}