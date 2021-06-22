package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * Модель пользователя (сотрудника) системы
 * @author rodkinsi
 */
@Entity
@Table(name = "employee")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    val id: Long,

    val username: String,

    @JsonIgnore
    var password: String?,

    val firstName: String,

    val lastName: String,

    val patrName: String,

    val personnelNumber: Long,

    val position: String,

    val phone: String,

    // TODO: заменить на Timezone? Как в бд хранить?
    val timezone: String,

    val isLead: Boolean,

    @ManyToOne
    val lead: User
)
