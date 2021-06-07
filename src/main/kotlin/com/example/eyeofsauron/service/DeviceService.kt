package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Device
import com.example.eyeofsauron.repository.DeviceRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с устройствами для считывания меток
 * @author rodkinsi
 */
@Service
class DeviceService(private val repository: DeviceRepository) {
    fun getAllDevices(): List<Device> = repository.findAll()

    fun getDeviceById(id: Long) = repository.findById(id)

    fun createDevice(device: Device) = repository.save(device)

    fun updateDevice(device: Device) = repository.save(device)

    fun delete(device: Device) = repository.delete(device)

    fun deleteDeviceById(id: Long) = repository.deleteById(id)
}