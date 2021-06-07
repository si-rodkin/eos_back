package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель маркера (метки)
 * @author rodkinsi
 */
@Entity
@Table(name = "markers")
data class Marker(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "markers_id_seq")
    @SequenceGenerator(name = "markers_id_seq", sequenceName = "markers_id_seq", allocationSize = 1)
    val id: Long,

    val name: String?,

    val rfid: String,

    @OneToOne
    val route: Route?
)
