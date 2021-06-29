package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.repository.RouteBypassRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Сервис для работы с обходами маршрутов
 * @author rodkinsi
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
            val dayRange = (nowWeekDay..(boundWeekDay - 2))
            for (day in dayRange) {
                spec = spec.or(dayIs(day))
            }

            // 3. выбираем обходы, которые будут в последнем дне с временем начала не позднее верхней границы выборки
            spec = spec.or(dayIs(boundWeekDay - 1).and(eqOrEarlierThan(limit)))

            return repository.findAll(spec)
        } else {
            // Ограничение на время по умолчанию: если выборка в пределах дня
            val spec = Specification.where(dayIs(nowWeekDay - 1).and(eqOrLaterThan(now)).and(eqOrEarlierThan(limit)))
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

    companion object {
        fun dayIs(day: Int) =
            Specification<RouteBypass> { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Int>("day"), day)
            }

        fun eqOrLaterThan(limit: LocalTime) =
            Specification<RouteBypass> { root, query, criteriaBuilder ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("bypassTime"), limit)
            }

        fun eqOrEarlierThan(limit: LocalTime) =
            Specification<RouteBypass> { root, query, criteriaBuilder ->
                criteriaBuilder.lessThanOrEqualTo(root.get("bypassTime"), limit)
            }
    }
}
