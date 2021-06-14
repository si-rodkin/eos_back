package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.entity.Statistics
import com.example.eyeofsauron.repository.StatisticsRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Сервис для работы со статистикой
 * @author rodkinsi
 */
@Service
class StatisticsService(
    private val repository: StatisticsRepository,
    private val markerReaderService: MarkerReaderService,
    private val markerService: MarkerService,
    private val checkPointService: CheckPointService
) {
    private var markerCreationMode = false;

    fun getAll(): List<Statistics> = repository.findAll()

    fun getById(id: Long) = repository.findById(id)

    fun getPlanned(): List<Statistics> = repository.findPlanned()

    fun getUnplanned(): List<Statistics> = repository.findUnplanned()

    fun commitStatistic(imei: String, rfid: String, checkpointId: Long) {
        // TODO: Уточнять который "Новый маркер" по счету
        if (markerCreationMode) markerService.create(Marker(null, "Новый маркер", rfid, null))
        else repository.save(
            Statistics(
                null,
                LocalDateTime.now() /*TODO*/,
                markerService.getByRfid(rfid),
                markerReaderService.getByImei(imei),
                checkPointService.getById(checkpointId).get()
            )
        )
    }

    fun toggleMarkerCreationMode() {
        markerCreationMode = !markerCreationMode
    }

    fun delete(statistics: Statistics) = repository.delete(statistics)

    fun deleteById(id: Long) = repository.deleteById(id)
}