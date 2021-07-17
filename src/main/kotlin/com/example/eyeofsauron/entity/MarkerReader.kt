package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель считывателя маркеров
 */
@Entity
data class MarkerReader(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marker_reader_id_seq")
    @SequenceGenerator(name = "marker_reader_id_seq", sequenceName = "marker_reader_id_seq", allocationSize = 1)
    val id: Long,

    val name: String?,

    val imei: String,

    val phone: String,

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(name = "route_marker_reader", inverseJoinColumns = [JoinColumn(name = "route_id")])
    val routes: List<Route>
)
