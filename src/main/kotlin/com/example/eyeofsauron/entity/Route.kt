package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель маршрута на охраняемом объекте
 * @author rodkinsi
 */
@Entity
@Table(name = "guard_routes")
data class Route(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guard_routes_id_seq")
    @SequenceGenerator(name = "guard_routes_id_seq", sequenceName = "guard_routes_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    @ManyToOne
    val guardedObject: Object,

    // TODO: сделать many-to-many линкующую таблицу для guard_routes - devices
    @ManyToMany
    val devices: List<Device>?
)
