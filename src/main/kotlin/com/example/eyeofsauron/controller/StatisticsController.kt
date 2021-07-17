package com.example.eyeofsauron.controller

import com.example.eyeofsauron.service.StatisticsService
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с событиями считывания меток устройствами
 */
@RestController
class StatisticsController(
    private val service: StatisticsService
) {
    @GetMapping("/api/commits")
    fun getAll() = service.getAll()

    @GetMapping("/api/commits/planned")
    fun getPlanned() = service.getPlanned()

    @GetMapping("/api/commits/unplanned")
    fun getUnplanned() = service.getUnplanned()

    @PostMapping("/api/commits/{imei}/{rfid}/{checkpointId}")
    fun commitMarker(
        @PathVariable imei: String,
        @PathVariable rfid: String,
        @PathVariable checkpointId: Long,
        @RequestBody bytes: ByteArray
    ) {
        service.commitStatistic(imei, rfid, checkpointId, bytes)
    }

    @PostMapping("/api/switch-marker-check-mode")
    fun switchMarkerCheckMode() = service.toggleMarkerCreationMode()
}