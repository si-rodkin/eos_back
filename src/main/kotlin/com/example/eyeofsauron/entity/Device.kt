package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель устройства считывания меток
 * @author rodkinsi
 */
@Entity
@Table(name = "devices")
data class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_id_seq")
    @SequenceGenerator(name = "devices_id_seq", sequenceName = "devices_id_seq", allocationSize = 1)
    val id: Long,

    val imei: String?,

    val name: String,

    val phone: String
)
