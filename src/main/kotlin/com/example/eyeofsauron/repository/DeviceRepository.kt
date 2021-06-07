package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Device
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DeviceRepository : JpaRepository<Device, Long>, JpaSpecificationExecutor<Device>