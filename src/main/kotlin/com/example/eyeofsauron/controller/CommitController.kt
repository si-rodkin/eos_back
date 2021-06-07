package com.example.eyeofsauron.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер для работы с событиями считывания меток устройствами
 */
@RestController
@RequestMapping(CommitController.uri)
class CommitController {
    @GetMapping
    fun getAll() = null // TODO

    @GetMapping("/planned")
    fun getPlanned() = null // TODO

    @GetMapping("/unplanned")
    fun getUnplanned() = null // TODO

    companion object {
        const val uri = "/api/commits"
    }
}