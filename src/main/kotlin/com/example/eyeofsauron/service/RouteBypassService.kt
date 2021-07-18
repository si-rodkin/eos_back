package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.entity.RouteBypass
import com.example.eyeofsauron.exception.EntityNotFoundException
import com.example.eyeofsauron.repository.RouteBypassRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDate
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
                // 4. Те, в диапазон дат обхода которых попадают дни выборки
                .or(
                    (0..makeDayRange(nowWeekDay, boundWeekDay).size - 1)
                        // TODO: учесть ограничение по времени
                        .map { number -> isInDateRange(LocalDate.now().plusDays(number.toLong())) }
                        .ifEmpty { null }
                        ?.reduce { resultSpec, forDay -> resultSpec.or(forDay) }
                )

            return repository.findAll(deviceIs(imei).and(spec)).run {
                // проход по номерам дней недели на которые выпадает выборка
                makeDayRange(nowWeekDay, boundWeekDay)
                    // выбираем обходы за данный день
                    .map { day ->
                        this.filter { bypass ->
                            // выпадает ли обход на рассматриваемый день
                            var result = false
                            // если задан день, смотрим по дню
                            if (bypass.day != null) {
                                result = bypass.day.contains(day.toString())
                            }
                            // если день не задан или по дню не нашли, ищем в диапазоне дат
                            if (!result && bypass.dateRangeStart != null) {
                                // вычисляем период между началом и концом диапазона
                                // если не задан конец диапазона, считаем, что обход назначен на конкретный день
                                val until = bypass.dateRangeStart.until(bypass.dateRangeEnd ?: bypass.dateRangeStart)
                                // если выборка ведётся в рамках недели
                                if (until.years == 0 && until.months == 0 && until.days <= 7) {
                                    // Создаем последовательность номеров дней недели между началом и концом и проверяем попадает ли в последовательность текущий день
                                    result = makeDayRange(
                                        bypass.dateRangeStart.dayOfWeek.value,
                                        bypass.dateRangeEnd?.dayOfWeek?.value ?: bypass.dateRangeStart.dayOfWeek.value
                                    )
                                        .contains(day + 1)
                                }
                                // TODO: рассмотреть возможность ведения выборки более чем на неделю
                            }
                            result
                        }
                    }
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

        /** Проверка попадает ли заданная дата в диапазон дат обхода */
        fun isInDateRange(date: LocalDate) =
            Specification<RouteBypass> { root, _, criteriaBuilder ->
                criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dateRangeStart"), date),
                    criteriaBuilder.lessThanOrEqualTo(root.get("dateRangeEnd"), date)
                )
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
