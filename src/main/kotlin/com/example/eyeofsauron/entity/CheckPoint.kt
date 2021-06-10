package com.example.eyeofsauron.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * Модель контрольной точки
 * @author rodkinsi
 */
@Entity
data class CheckPoint(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_point_id_seq")
    @SequenceGenerator(name = "check_point_id_seq", sequenceName = "check_point_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    val readTime: LocalDateTime,

    val allowanceTime: LocalDateTime,

    val bypassDate: Date,

    @ManyToOne
    val route: Route,

    @ManyToOne
    val marker: Marker,

    @ManyToMany
    val markerReader: List<MarkerReader>
)
