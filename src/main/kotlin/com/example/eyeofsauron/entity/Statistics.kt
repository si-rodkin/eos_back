package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonFormat
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

    @ManyToOne
    val marker: Marker,

    @ManyToOne
    val markerReader: MarkerReader,

    @ManyToOne
    val checkPoint: CheckPoint
)
