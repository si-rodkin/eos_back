package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Statistics
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface StatisticsRepository : JpaRepository<Statistics, Long>, JpaSpecificationExecutor<Statistics>