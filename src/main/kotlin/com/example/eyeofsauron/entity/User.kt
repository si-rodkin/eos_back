package com.example.eyeofsauron.entity

import javax.persistence.*

/**
 * Модель пользователя (сотрудника) системы
 * @author rodkinsi
 */
@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    val id: Long,

    val username: String,

    private val password: String?,

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
