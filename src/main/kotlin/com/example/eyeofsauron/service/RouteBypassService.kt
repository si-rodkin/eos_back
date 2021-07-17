package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.exception.EntityNotFoundException
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

    /**
     * Выборка обходов устройства с [imei] за ближайшие [limit] часов
     */
    fun getByImeiAndLimit(imei: String, limit: Long): List<RouteBypass> {
        val nowWeekDay = LocalDateTime.now().dayOfWeek.value - 1
        val boundWeekDay = LocalDateTime.now().plusHours(limit).dayOfWeek.value - 1

        // Если выборка затрагивает несколько дней, то
        if (nowWeekDay != boundWeekDay) {
            // 1. выбираем обходы, которые остались в этом дне
            val spec = Specification.where(startTimeGte(LocalTime.now()).and(dayIs(nowWeekDay)))
                // 2. выбираем обходы которые должны быть между днем запроса и днем лимита
                .or(makeDayRange(nowWeekDay, (boundWeekDay - 1))
                    .map { day -> dayIs(day) }
                    .reduce { result, spec -> result.or(spec) }
                )
                // 3. выбираем обходы, которые будут в последнем дне с временем начала не позднее верхней границы выборки
                .or(dayIs(boundWeekDay).and(startTimeLte(LocalTime.now().plusHours(limit))))

            return repository.findAll(deviceIs(imei).and(spec)).run {
                // проход по номерам дней недели на которые выпадает выборка
                makeDayRange(nowWeekDay, boundWeekDay)
                    // выбираем обходы за данный день
                    .map { day -> this.filter { it.day.contains(day.toString()) } }
                    // сортируем обходы за данный день по времени начала
                    .map { it.sortedBy { it.bypassTime } }
                    // Собираем списки из предыдущих этапов в единый список
                    .reduce { result, part -> result + part }
            }
        }

        // Если выборка в пределах дня
        return repository.findAll(
            Specification.where(
                deviceIs(imei)
                    .and(dayIs(nowWeekDay))
                    // Ограничение на время по умолчанию
                    .and(startTimeGte(LocalTime.now()))
                    .and(startTimeLte(LocalTime.now().plusHours(limit)))
            )
        )
    }

    fun create(routeBypass: RouteBypass) = repository.save(routeBypass)

    fun update(routeBypass: RouteBypass) {
        if (!repository.existsById(routeBypass.id))
            throw EntityNotFoundException()
        repository.save(routeBypass)
    }

    fun delete(routeBypass: RouteBypass) = repository.delete(routeBypass)

    fun deleteById(id: Long) = repository.deleteById(id)

    /**
     * Создать правильную последовательность номеров дней недели с начальным номеров [begin] и конечным [endP]
     */
    private fun makeDayRange(begin: Int, endP: Int): List<Int> {
        val end = if (begin > endP) endP + 7 else endP
        return (begin..end).map { it % 7 }
    }

    companion object {
        /** Спецификация ограничения выборки по [imei] устройства */
        fun deviceIs(imei: String) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<MarkerReader>("markerReader").get<String>("imei"), imei)
            }

        /** Спецификация ограничения выборки по дню обхода */
        fun dayIs(day: Int) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.like(root.get("day"), "%$day%")
            }

        /**
         * Спецификация ограничения выборки по времени начала обхода
         * (время начала обхода больше или равно заданному [limit])
         */
        fun startTimeGte(limit: LocalTime) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("bypassTime"), limit)
            }

        /**
         * Спецификация ограничения выборки по времени начала обхода
         * (время начала обхода меньше или равно заданному [limit])
         */
        fun startTimeLte(limit: LocalTime) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.lessThanOrEqualTo(root.get("bypassTime"), limit)
            }
    }
}
