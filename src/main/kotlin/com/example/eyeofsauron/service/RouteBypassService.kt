package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.repository.RouteBypassRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Сервис для работы с обходами маршрутов
 */
@Service
class RouteBypassService(private val repository: RouteBypassRepository) {
    fun getAll(): List<RouteBypass> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun getByRoute(routeId: Long) = repository.findByRouteId(routeId)

    fun getByImeiAndLimit(imei: String, limitH: Long): List<RouteBypass> {
        val now = LocalTime.now()
        val limit = LocalTime.now().plusHours(limitH)
        val nowWeekDay = LocalDateTime.now().dayOfWeek.value
        val boundWeekDay = LocalDateTime.now().plusHours(limitH).dayOfWeek.value

        /** Если выборка затрагивает несколько дней, то */
        if (nowWeekDay != boundWeekDay) {
            // 1. выбираем обходы, которые остались в этом дне
            var spec = Specification.where(eqOrLaterThan(now).and(dayIs(nowWeekDay - 1)))

            // 2. выбираем обходы которые должны быть между днем запроса и днем лимита
            val dayRange = makeDayRange(nowWeekDay, (boundWeekDay - 2))
            for (day in dayRange) {
                spec = spec.or(dayIs(day))
            }

            // 3. выбираем обходы, которые будут в последнем дне с временем начала не позднее верхней границы выборки
            spec = spec.or(dayIs(boundWeekDay - 1).and(eqOrEarlierThan(limit)))

            val routeBypasses = repository.findAll(deviceIs(imei).and(spec))
            val result = mutableMapOf<Int, List<RouteBypass>>()

            for (day in makeDayRange(nowWeekDay - 1, boundWeekDay - 1)) {
                val localResult = mutableListOf<RouteBypass>()
                for (bypass in routeBypasses)
                    if (bypass.day.contains(day.toString()))
                        localResult.add(bypass);
                result.put(day, localResult)
            }

            val veryResult = mutableListOf<RouteBypass>()
            result.map { it.value }.map { it.sortedBy { it.bypassTime } }.map { veryResult.addAll(it) }
            return veryResult
        } else {
            // Ограничение на время по умолчанию: если выборка в пределах дня
            val spec = Specification.where(deviceIs(imei).and(dayIs(nowWeekDay)).and(eqOrLaterThan(now)).and(eqOrEarlierThan(limit)))
            return  repository.findAll(spec)
        }
    }

    fun create(routeBypass: RouteBypass) = repository.save(routeBypass)

    fun update(routeBypass: RouteBypass) {
        if(!repository.existsById(routeBypass.id))
            throw(Exception("Record not found!"))
        repository.save(routeBypass)
    }

    fun delete(routeBypass: RouteBypass) = repository.delete(routeBypass)

    fun deleteById(id: Long) = repository.deleteById(id)

    private fun makeDayRange(begin: Int, endP: Int): List<Int> {
        val result = mutableListOf<Int>()
        val end = if (begin > endP) endP + 7 else endP;
        for (i in begin..end) result.add(i % 7)
        return result
    }

    companion object {
        fun deviceIs(imei: String) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<MarkerReader>("markerReader").get<String>("imei"), imei)
            }

        fun dayIs(day: Int) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.like(root.get("day"), "%$day%")
            }

        fun eqOrLaterThan(limit: LocalTime) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("bypassTime"), limit)
            }

        fun eqOrEarlierThan(limit: LocalTime) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.lessThanOrEqualTo(root.get("bypassTime"), limit)
            }
    }
}
