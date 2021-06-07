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
    fun getAllCheckPoints(): List<CheckPoint> = repository.findAll()

    fun getCheckPointById(id: Long) = repository.findById(id)

    fun createCheckPoint(checkPoint: CheckPoint) = repository.save(checkPoint)

    fun updateCheckPoint(checkPoint: CheckPoint) = repository.save(checkPoint)

    fun delete(checkPoint: CheckPoint) = repository.delete(checkPoint)

    fun deleteCheckPointById(id: Long) = repository.deleteById(id)
}