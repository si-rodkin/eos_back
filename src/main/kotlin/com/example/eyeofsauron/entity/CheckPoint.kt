package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalTime
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val readTime: LocalTime,

    val allowanceTime: Int,

    val lateTime: Int,

    //@JsonIgnoreProperties("name", "startBypassTime", "endBypassTime", "day", "notify", "route", "markerReader")
    @ManyToOne
    val routeBypass: RouteBypass,

    //@JsonIgnoreProperties("name", "rfid", "route")
    @ManyToOne
    val marker: Marker,
)
