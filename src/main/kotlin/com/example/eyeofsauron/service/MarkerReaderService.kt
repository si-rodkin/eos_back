package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.repository.MarkerReaderRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с устройствами для считывания меток
 * @author rodkinsi
 */
@Service
class MarkerReaderService(private val repository: MarkerReaderRepository) {
    fun getAll(): List<MarkerReader> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun getByImei(imei: String) = repository.findByImei(imei)

    fun create(markerReader: MarkerReader) = repository.save(markerReader)

    fun update(markerReader: MarkerReader) = repository.save(markerReader)

    fun delete(markerReader: MarkerReader) = repository.delete(markerReader)

    fun deleteById(id: Long) = repository.deleteById(id)
}