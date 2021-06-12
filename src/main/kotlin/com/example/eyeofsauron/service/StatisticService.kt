package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Statistic
import com.example.eyeofsauron.repository.StatisticRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы со статистикой
 * @author rodkinsi
 */
@Service
class StatisticService(private val repository: StatisticRepository) {
    fun getAll(): List<Statistic> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun getPlanned(): List<Statistic> = repository.findPlanned()

    fun getUnplanned(): List<Statistic> = repository.findUnplanned()

    fun create(statistic: Statistic) = repository.save(statistic)

    fun update(statistic: Statistic) = repository.save(statistic)

    fun delete(statistic: Statistic) = repository.delete(statistic)

    fun deleteById(id: Long) = repository.deleteById(id)
}