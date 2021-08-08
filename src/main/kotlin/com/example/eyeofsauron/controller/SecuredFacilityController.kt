package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.PermissionService
import com.example.eyeofsauron.service.SecuredFacilityService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.AccessControlException
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
    fun getAll(@RequestHeader authorization: String): List<SecuredFacility> =
        service.getAll().filter { item -> permission.hasAccess(item, authorization) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, @RequestHeader authorization: String): Optional<SecuredFacility>? {
        if (permission.hasAccess(service.getById(id).get(), authorization))
            return service.getById(id)
        throw AccessControlException("Unable to get element")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody securedFacility: SecuredFacility) = service.create(securedFacility)

    @PutMapping
    fun update(@RequestBody securedFacility: SecuredFacility, @RequestHeader authorization: String) {
        if (permission.hasAccess(securedFacility, authorization))
            service.update(securedFacility)
        else throw AccessControlException("Unable to edit element")
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long, @RequestHeader authorization: String) {
        if (permission.hasAccess(service.getById(id).get(), authorization))
            service.deleteById(id)
        else throw AccessControlException("Unable to delete element")
    }

    companion object {
        const val uri = "/api/secured-facilities"
    }
}