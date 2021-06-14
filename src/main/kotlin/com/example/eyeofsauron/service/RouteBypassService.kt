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

    fun getByImeiAndLimit(imei: String, limitH: Long): List<RouteBypass> {
        val now = LocalTime.now()
        val limit = LocalTime.now().plusHours(limitH)
        val nowWeekDay = LocalDateTime.now().dayOfWeek.value
        val boundWeekDay = LocalDateTime.now().plusHours(limitH).dayOfWeek.value

        val bypasses: List<RouteBypass>
        /** Если выборка затрагивает несколько дней, то */
        if (nowWeekDay != boundWeekDay) {
            //            """ 1. выбираем обходы, которые остались в этом дне"""
            //            timeBound = Q(days=now.isoweekday() - 1) & Q(start_time__gte=now)
            var spec = Specification.where(eqOrLaterThan(now).and(dayIs(nowWeekDay - 1)))

            val dayRange = (nowWeekDay..(boundWeekDay - 2))
            for (day in dayRange) {
                spec = spec.or(dayIs(day))
            }
            //            """ 2. выбираем обходы которые должны быть между днем запроса и днем лимита"""
            //            for day in range(now.isoweekday(), limit.isoweekday() - 1):
            //                timeBound |= Q(days=day)

            spec = spec.or(dayIs(boundWeekDay - 1).and(eqOrEarlierThan(limit)))
            //            """ 3. выбираем обходы, которые будут в последнем дне с временем начала не позднее верхней границы выборки"""
            //            timeBound |= Q(days=limit.isoweekday() - 1) & Q(start_time__lte=limit)

            return repository.findAll(spec)
        } else {
            //            """Ограничение на время по умолчанию: если выборка в пределах дня"""
            //            timeBound = Q(days=now.isoweekday() - 1) & Q(start_time__gte=now) & Q(start_time__lte=limit)
            val spec = Specification.where(dayIs(nowWeekDay - 1).and(eqOrLaterThan(now)).and(eqOrEarlierThan(limit)))
            return  repository.findAll(spec)
        }
    }

    fun create(routeBypassBypass: RouteBypass) = repository.save(routeBypassBypass)

    fun update(routeBypassBypass: RouteBypass) = repository.save(routeBypassBypass)

    fun delete(routeBypassBypass: RouteBypass) = repository.delete(routeBypassBypass)

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