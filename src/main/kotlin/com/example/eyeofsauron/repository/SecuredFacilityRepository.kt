package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.SecuredFacility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface SecuredFacilityRepository : JpaRepository<SecuredFacility, Long>, JpaSpecificationExecutor<SecuredFacility>