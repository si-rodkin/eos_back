package com.example.eyeofsauron.controller

import com.example.eyeofsauron.service.StatisticService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер для работы с событиями считывания меток устройствами
 */
@RestController
@RequestMapping(StatisticController.uri)
class StatisticController(private val service: StatisticService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/planned")
    fun getPlanned() = service.getPlanned()

    @GetMapping("/unplanned")
    fun getUnplanned() = service.getUnplanned()

    companion object {
        const val uri = "/api/statistic"
    }
}