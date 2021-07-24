package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

/**
 * Модель охраняемого объекта
 */
@Entity
data class SecuredFacility(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secured_facility_id_seq")
    @SequenceGenerator(name = "secured_facility_id_seq", sequenceName = "secured_facility_id_seq", allocationSize = 1)
    override var id: Long,

    val name: String,

    val itn: String,

    @Column
    override var ownerId: Long
): Ownerable
