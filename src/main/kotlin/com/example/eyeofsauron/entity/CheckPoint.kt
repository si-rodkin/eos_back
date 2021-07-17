package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime
import javax.persistence.*

/**
 * Модель контрольной точки
 */
@Entity
data class CheckPoint(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_point_id_seq")
    @SequenceGenerator(name = "check_point_id_seq", sequenceName = "check_point_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val readTime: LocalTime,

    val allowanceTime: Int,

    val lateTime: Int,

    @ManyToOne
    val routeBypass: RouteBypass,

    @ManyToOne
    val marker: Marker,
)
