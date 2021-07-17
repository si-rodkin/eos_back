package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.entity.RouteBypass
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Сервис для работы со считывателями маркеров
 */
@Service
class SyncService(
    private val bypassService: RouteBypassService,
    private val checkPointService: CheckPointService
) {
    /**
     * Сформировать отчёт о серверном времени
     */
    fun syncDate(zoneId: ZoneId) = mapOf(
        "time" to LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")),
        "weekday" to LocalDateTime.now().dayOfWeek.value.toString()
    )

    /**
     * Сформировать отчет о маршруте устройства с [imei] на ближайшие [limit] часов
     */
    fun createRouteForMarkerReaderByLimit(imei: String, limit: Long): Map<String, Any> {
        bypassService.getByImeiAndLimit(imei, limit).run {
            val checkpoints = getCheckPoints(this).map {
                mapOf(
                    "start_time" to it.readTime.format(DateTimeFormatter.ISO_LOCAL_TIME),
                    "id" to it.routeBypass.id,
                    "name" to it.name,
                    "rfid" to it.marker.rfid
                )
            }

            return mapOf(
                "marker_count" to checkpoints.size,
                "begins_count" to this.size,
                "markers" to checkpoints,
                "round_begins" to this.map {
                    mapOf(
                        "start_time" to it.bypassTime.format(DateTimeFormatter.ISO_LOCAL_TIME),
                        "end_time" to it.bypassEndTime.format(DateTimeFormatter.ISO_LOCAL_TIME),
                        "notify" to it.notify
                    )
                }
            )
        }
    }

    private fun getCheckPoints(routeBypass: List<RouteBypass>): List<CheckPoint> {
        return routeBypass.stream()
            .map { checkPointService.getByBypass(listOf(it.id)) }
            .reduce { result, part -> result + part }
            .orElse(listOf())
    }
}