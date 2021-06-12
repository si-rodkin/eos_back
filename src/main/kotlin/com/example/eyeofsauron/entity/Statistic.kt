package com.example.eyeofsauron.entity
import java.time.LocalTime
import javax.persistence.*

/**
 * Модель статистики
 * @author rodkinsi
 */
@Entity
data class Statistic(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistic_id_seq")
    @SequenceGenerator(name = "statistic_id_seq", sequenceName = "statistic_id_seq", allocationSize = 1)
    val id: Long,

    val date: LocalTime,

    @ManyToOne
    val marker: Marker,

    @ManyToOne
    val markerReader: MarkerReader,

    @ManyToOne
    val checkPoint: CheckPoint
)
