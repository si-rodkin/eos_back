package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель считывателя маркеров
 * @author rodkinsi
 */
@Entity
data class MarkerReader(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "markers_reader_id_seq")
    @SequenceGenerator(name = "marker_reader_id_seq", sequenceName = "marker_reader_id_seq", allocationSize = 1)
    val id: Long,

    val name: String?,

    val imei: String,

    val phone: String,

    @ManyToMany
    val checkPoints: List<CheckPoint>
)
