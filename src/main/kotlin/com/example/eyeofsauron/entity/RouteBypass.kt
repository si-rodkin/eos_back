package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import java.time.LocalTime
import javax.persistence.*

/**
 * Модель обхода по маршруту
 * @author rodkinsi
 */
@Entity
data class RouteBypass(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_bypass_id_seq")
    @SequenceGenerator(name = "route_bypass_id_seq", sequenceName = "route_bypass_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,


    @JsonSerialize(using = LocalTimeSerializer::class)
    @JsonDeserialize(using = LocalTimeDeserializer::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val startBypassTime: LocalTime,

    @JsonSerialize(using = LocalTimeSerializer::class)
    @JsonDeserialize(using = LocalTimeDeserializer::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val endBypassTime: LocalTime,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val bypassEndTime: LocalTime,

    val day: Int,

    val notify: Boolean,

    //@JsonIgnoreProperties("name", "securedFacility")
    @ManyToOne
    val route: Route,

    //@JsonIgnoreProperties("name", "imei", "phone", "routes")
    @OneToOne
    val markerReader: MarkerReader
)
