package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель охраняемого объекта
 * @author rodkinsi
 */
@Entity
@Table(name = "guard_objects")
data class Object(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guard_objects_id_seq")
    @SequenceGenerator(name = "guard_objects_id_seq", sequenceName = "guard_objects_id_seq", allocationSize = 1)
    val id: Long,

    val name: String,

    val itn: String
)
