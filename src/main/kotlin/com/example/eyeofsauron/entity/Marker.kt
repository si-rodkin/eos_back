package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель маркера (метки)
 * @author rodkinsi
 */
@Entity
data class Marker(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marker_id_seq")
    @SequenceGenerator(name = "marker_id_seq", sequenceName = "marker_id_seq", allocationSize = 1)
    val id: Long,

    val name: String?,

    val rfid: String,

    @ManyToOne
    val route: Route?
)
