package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.PermissionService
import com.example.eyeofsauron.service.SecuredFacilityService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Контроллер для работы с охраняемыми объектами
 */
@RestController
@RequestMapping(SecuredFacilityController.uri)
class SecuredFacilityController(
    private val service: SecuredFacilityService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Optional<SecuredFacility>? {
        if (permission.hasAccess(id, service.getById(id).get()))
            return service.getById(id)
        return null
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody securedFacility: SecuredFacility) {
        val id: Long = securedFacility.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.create(securedFacility)
    }

    @PutMapping
    fun update(@RequestBody securedFacility: SecuredFacility) {
        val id: Long = securedFacility.id
        if (permission.hasAccess(id, service.getById(id).get()))
            service.update(securedFacility)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        if (permission.hasAccess(id, service.getById(id).get()))
            service.deleteById(id)
    }

    companion object {
        const val uri = "/api/secured-facilities"
    }
}