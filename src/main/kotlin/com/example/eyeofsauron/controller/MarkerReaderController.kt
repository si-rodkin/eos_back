package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.service.MarkerReaderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с устройствами считывания меток {@link MarkerReader}
 * @author rodkinsi
 */
@RestController
@RequestMapping(MarkerReaderController.uri)
class MarkerReaderController(private val service: MarkerReaderService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody markerReader: MarkerReader) = service.create(markerReader)

    @PutMapping
    fun update(@RequestBody markerReader: MarkerReader) = service.update(markerReader)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.deleteById(id)

    companion object {
        const val uri = "/api/marker-readers"
    }
}