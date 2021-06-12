package com.example.eyeofsauron.entity
import javax.persistence.*


@Entity
data class Statistics(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistics_id_seq")
    @SequenceGenerator(name = "statistics_id_seq", sequenceName = "statistics_id_seq", allocationSize = 1)
    val id: Long,

    @ManyToOne
    val marker: Marker,

    @ManyToOne
    val markerReader: MarkerReader,

    @ManyToOne
    val checkPoint: CheckPoint
)
