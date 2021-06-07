package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Object
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ObjectRepository : JpaRepository<Object, Long>, JpaSpecificationExecutor<Object>