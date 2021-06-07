package com.example.eyeofsauron.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Модель события считывания метки устройством
 * @author rodkinsi
 */
@Entity
@Table(name = "statistics")
data class Commit(
    @Id
    val id: Long,

    @ManyToOne
    val checkPoint: CheckPoint,

    @ManyToOne
    val device: Device,

    @ManyToOne
    val commit: Commit,

    val date: LocalDateTime
)
