package com.example.eyeofsauron.entity

import java.time.LocalTime
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

    val readTime: LocalTime,

    val allowanceTime: LocalTime,

    val lateTime: LocalTime,

    @ManyToOne
    val routeBypass: RouteBypass,

    @ManyToOne
    val marker: Marker,

    @OneToOne
    val markerReader: MarkerReader
)
