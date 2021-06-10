package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.MarkerReader
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MarkerReaderRepository : JpaRepository<MarkerReader, Long>, JpaSpecificationExecutor<MarkerReader>