package com.example.eyeofsauron.controller

import com.example.eyeofsauron.service.SyncService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneId

/**
 * Контроллер для радоты со считывателями маркеров
 */
@RestController
class SyncController(
    private val service: SyncService
) {
    @GetMapping("/api/sync/date")
    fun syncDate(@RequestParam(defaultValue = "Europe/Moscow") zoneId: ZoneId) = service.syncDate(zoneId)

    @GetMapping("/api/sync/route/{imei}")
    fun syncRoute(
        @PathVariable imei: String,
        @RequestParam(defaultValue = "24") limit: Long
    ) = service.createRouteForMarkerReaderByLimit(imei, limit)
}
