package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.repository.CheckPointRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с контрольными точками
 * @author rodkinsi
 */
@Service
class CheckPointService(private val repository: CheckPointRepository) {
    fun getAll(): List<CheckPoint> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun create(checkPoint: CheckPoint) = repository.save(checkPoint)

    fun update(checkPoint: CheckPoint) = repository.save(checkPoint)

    fun delete(checkPoint: CheckPoint) = repository.delete(checkPoint)

    fun deleteById(id: Long) = repository.deleteById(id)
}