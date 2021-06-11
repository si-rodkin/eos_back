package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель маршрута на охраняемом объекте
 * @author rodkinsi
 */
@Entity
data class Route(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_id_seq")
    @SequenceGenerator(name = "route_id_seq", sequenceName = "route_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    @ManyToOne
    val securedFacility: SecuredFacility,

    @ManyToMany(mappedBy = "route_marker_reader")
    val markerReaders: List<MarkerReader>
)
