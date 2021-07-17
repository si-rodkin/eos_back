package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime
import javax.persistence.*

/**
 * Модель обхода по маршруту
 */
@Entity
data class RouteBypass(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_bypass_id_seq")
    @SequenceGenerator(name = "route_bypass_id_seq", sequenceName = "route_bypass_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val bypassTime: LocalTime,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val bypassEndTime: LocalTime,

    val day: String,

    val notify: Boolean,

    @ManyToOne
    val route: Route,

    @OneToOne
    val markerReader: MarkerReader
)
