package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Модель статистики
 * @author rodkinsi
 */
@Entity
data class Statistics(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistics_id_seq")
    @SequenceGenerator(name = "statistics_id_seq", sequenceName = "statistics_id_seq", allocationSize = 1)
    val id: Long?,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss")
    val date: LocalDateTime,

    //@JsonIgnoreProperties("rfid", "route")
    @ManyToOne
    val marker: Marker,

    //@JsonIgnoreProperties("name", "imei", "phone", "routes")
    @ManyToOne
    val markerReader: MarkerReader,

    //@JsonIgnoreProperties("name", "readTime", "allowanceTime", "lateTime", "routeBypass", "marker")
    @ManyToOne
    val checkPoint: CheckPoint
)
