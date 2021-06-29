package com.example.eyeofsauron.service

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Сервис для работы со считывателями маркеров
 *
 * @author rodkinsi
 */
@Service
class SyncService(
    private val bypassService: RouteBypassService,
    private val checkPointService: CheckPointService
) {

    // TODO: после разнесения на модули, выделить сущности для ответов

    /**
     * Сформировать отчёт о серверном времени
     */
    fun syncDate() = mapOf(
        "time" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")),
        "weekday" to LocalDateTime.now().dayOfWeek.value.toString()
    )

    /**
     * Сформировать отчет о маршруте устройства с [imei] на ближайшие [limit] часов
     */
    fun createRouteForMarkerReaderByLimit(imei: String, limit: Long): Map<String, Any> {
        val bypasses = bypassService.getByImeiAndLimit(imei, limit)
        val checkPoints = checkPointService.getByBypass(bypasses.map { it.id })

        val checkpoints = checkPoints.map {
            mapOf(
                "start_time" to it.readTime.format(DateTimeFormatter.ISO_LOCAL_TIME),
                "id" to it.routeBypass.id,
                "name" to it.name,
                "rfid" to it.marker.rfid
            )
        }

        val roundBegins = bypasses.map {
            mapOf(
                "start_time" to it.bypassTime.format(DateTimeFormatter.ISO_LOCAL_TIME),
                "end_time" to it.bypassEndTime.format(DateTimeFormatter.ISO_LOCAL_TIME),
                "notify" to it.notify
            )
        }

        return mapOf(
            "marker_count" to checkPoints.size,
            "begins_count" to bypasses.size,
            "markers" to checkpoints,
            "round_begins" to roundBegins
        )
    }
}