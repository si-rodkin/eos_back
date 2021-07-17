package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.repository.SecuredFacilityRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с охраняемыми объектами
 */
@Service
class SecuredFacilityService(private val repository: SecuredFacilityRepository) {
    fun getAll(): List<SecuredFacility> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun create(securedFacility: SecuredFacility) = repository.save(securedFacility)

    fun update(securedFacility: SecuredFacility) {
        if(!repository.existsById(securedFacility.id))
            throw(Exception("Record not found!"))
        repository.save(securedFacility)
    }

    fun delete(securedFacility: SecuredFacility) = repository.delete(securedFacility)

    fun deleteById(id: Long) = repository.deleteById(id)
}