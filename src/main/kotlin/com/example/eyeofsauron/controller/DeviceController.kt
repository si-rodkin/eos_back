package com.example.eyeofsauron.controller

import com.example.eyeofsauron.entity.Device
import com.example.eyeofsauron.service.DeviceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Контроллер для работы с устройствами считывания меток {@link Device}
 * @author rodkinsi
 */
@RestController
@RequestMapping(DeviceController.uri)
class DeviceController(private val service: DeviceService) {
    @GetMapping
    fun getAllDevices() = service.getAllDevices()

    @GetMapping("/{id}")
    fun getDevice(@PathVariable id: Long) = service.getDeviceById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDevice(@RequestBody device: Device) = service.createDevice(device)

    @PutMapping
    fun updateDevice(@RequestBody device: Device) = service.updateDevice(device)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDevice(@PathVariable id: Long) = service.deleteDeviceById(id)

    companion object {
        const val uri = "/api/devices"
    }
}