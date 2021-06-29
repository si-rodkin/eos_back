package com.example.eyeofsauron.entity

import com.fasterxml.jackson.annotation.JsonProperty
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String?,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    var passwordRepeat: String?,

    val firstName: String,

    val lastName: String,

    val patrName: String?,

    val personnelNumber: Long,

    val position: String,

    val phone: String,

    val email: String?,

    // TODO: заменить на Timezone? Как в бд хранить?
    val timezone: String,

    @Column(name = "isLead")
    @JsonProperty("isLead")
    val leading: Boolean,

    @Column(name = "lead_id")
    val lead: Long?
)
