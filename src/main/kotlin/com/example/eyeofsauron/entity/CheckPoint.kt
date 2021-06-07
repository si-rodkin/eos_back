package com.example.eyeofsauron.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Модель контрольной точки
 * @author rodkinsi
 */
@Entity
@Table(name = "check_points")
data class CheckPoint(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_points_id_seq")
    @SequenceGenerator(name = "check_points_id_seq", sequenceName = "check_points_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    val days: Long,

    val startTime: LocalDateTime,

    val endTime: LocalDateTime?,

    val timeAllowance: Long,

    val lateTime: Long,

    @OneToOne
    val marker: Marker,

    @OneToOne
    val device: Device,

    @ManyToOne
    val route: Route
)
