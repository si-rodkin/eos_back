package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.service.MarkerReaderService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Контроллер для работы с устройствами считывания меток {@link MarkerReader}
 */
@RestController
@RequestMapping(MarkerReaderController.uri)
class MarkerReaderController(
    private val service: MarkerReaderService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Optional<MarkerReader>? {
        if (permission.hasAccess(id, service.getById(id).get()))
            return service.getById(id)
        return null
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody markerReader: MarkerReader) {
        val id: Long = markerReader.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.create(markerReader)
    }

    @PutMapping
    fun update(@RequestBody markerReader: MarkerReader) {
        val id: Long = markerReader.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.update(markerReader)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        if (permission.hasAccess(id, service.getById(id).get()))
            service.deleteById(id)
    }

    companion object {
        const val uri = "/api/marker-readers"
    }
}