package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.CheckPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CheckPointRepository : JpaRepository<CheckPoint, Long>, JpaSpecificationExecutor<CheckPoint>