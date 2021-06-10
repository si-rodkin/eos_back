package com.example.eyeofsauron.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class RouteBypass(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_bypass_id_seq")
    @SequenceGenerator(name = "route_bypass_id_seq", sequenceName = "route_bypass_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    val bypassTime: LocalDateTime,

    val notify: Boolean,

    @ManyToOne
    val route: Route
)
