package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.CheckPointService
import com.example.eyeofsauron.service.PermissionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.AccessControlException
import java.util.*

/**
 * Контроллер для работы с контрольными точками
 */
@RestController
@RequestMapping(CheckPointController.uri)
class CheckPointController(
    private val service: CheckPointService,
    private val permission: PermissionService
) {
    @GetMapping
    fun getAll(@RequestHeader authorization: String): List<CheckPoint> {
        val all: List<CheckPoint> = service.getAll()
        val own = mutableListOf<CheckPoint>()

        all.forEach{
            val id: Long = it.id
            if(permission.hasAccess(id, service.getById(id).get(), authorization))
                own.add(it)
        }
        return own
    }

    @GetMapping("/by-routebypass/{id}")
    fun getByBypass(@PathVariable id: Long, @RequestHeader authorization: String): List<CheckPoint> {
        val all: List<CheckPoint> = service.getByBypass(listOf(id))
        val own = mutableListOf<CheckPoint>()

        all.forEach{
            val id: Long = it.id
            if(permission.hasAccess(id, service.getById(id).get(), authorization))
                own.add(it)
        }
        return own
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, @RequestHeader authorization: String): Optional<CheckPoint>? {
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            return service.getById(id)
        throw AccessControlException("Unable to get element")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody checkPoint: CheckPoint) = service.create(checkPoint)


    @PutMapping
    fun update(@RequestBody checkPoint: CheckPoint, @RequestHeader authorization: String) {
        val id: Long = checkPoint.id
        if (permission.hasAccess(id, service.getById(id).get(), authorization))
            service.update(checkPoint)
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
        const val uri = "/api/check-points"
    }
}