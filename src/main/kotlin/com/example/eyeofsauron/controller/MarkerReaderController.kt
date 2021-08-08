package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.service.MarkerReaderService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.AccessControlException
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
    fun getAll(@RequestHeader authorization: String): List<MarkerReader> =
        service.getAll()
            .filter { item ->
                permission.hasAccess(item.id, service.getById(item.id).get(), authorization)
            }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, @RequestHeader authorization: String): Optional<MarkerReader>? {
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            return service.getById(id)
        throw AccessControlException("Unable to get element")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody markerReader: MarkerReader) = service.create(markerReader)

    @PutMapping
    fun update(@RequestBody markerReader: MarkerReader, @RequestHeader authorization: String) {
        val id: Long = markerReader.id
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            service.update(markerReader)
        else throw AccessControlException("Unable to edit element")
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long, @RequestHeader authorization: String) {
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            service.deleteById(id)
        else throw AccessControlException("Unable to delete element")
    }

    companion object {
        const val uri = "/api/marker-readers"
    }
}