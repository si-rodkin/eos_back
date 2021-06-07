package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Object
import com.example.eyeofsauron.repository.ObjectRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с охраняемыми объектами
 * @author rodkinsi
 */
@Service
class ObjectService(private val repository: ObjectRepository) {
    fun getAllObjects(): List<Object> = repository.findAll()

    fun getObjectById(id: Long) = repository.findById(id)

    fun createObject(obj: Object) = repository.save(obj)

    fun updateObject(obj: Object) = repository.save(obj)

    fun delete(obj: Object) = repository.delete(obj)

    fun deleteObjectById(id: Long) =
        repository.deleteById(id)
}