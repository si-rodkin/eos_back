package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель охраняемого объекта
 */
@Entity
data class SecuredFacility(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secured_facility_id_seq")
    @SequenceGenerator(name = "secured_facility_id_seq", sequenceName = "secured_facility_id_seq", allocationSize = 1)
    var id: Long,

    val name: String,

    val itn: String
)
