package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.MarkerService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.AccessControlException
import java.util.*

/**
 * Контроллер для работы с маркерами {@link Marker}
 */
@RestController
@RequestMapping(MarkerController.uri)
class MarkerController(
    private val service: MarkerService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll(@RequestHeader authorization: String): List<Marker> =
    service.getAll()
        .filter { item ->
            item.id?.let { permission.hasAccess(it, service.getById(item.id).get(), authorization) } == true
    }


    @GetMapping("/free-or/{id}")
    fun getFreeOrRouteMarkers(@PathVariable id: Long, @RequestHeader authorization: String): List<Marker> =
        service.getFreeOrRouteMarkers(id)
            .filter { item ->
                item.id?.let { permission.hasAccess(it, service.getById(item.id).get(), authorization) } == true
            }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, @RequestHeader authorization: String): Optional<Marker>? {
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            return service.getById(id)
        throw AccessControlException("Unable to get element")
    }

    @PutMapping
    fun update(@RequestBody marker: Marker, @RequestHeader authorization: String) {
        val id: Long? = marker.id
        if (id?.let { permission.hasAccess(it, service.getById(id).get(), authorization) } == true)
            service.update(marker)
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
        const val uri = "/api/markers"
    }
}
