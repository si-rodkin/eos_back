package com.example.eyeofsauron

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Assert
import org.junit.jupiter.api.Test

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TestEntity(
    val lupa: String,
    val pupa: String? = null
)

class JacksonLearning: Assert() {
    @Test
    fun only_nonnull_field_serialization() {
        val entity = TestEntity("pupa")

        val serializedEntity = jacksonObjectMapper().writeValueAsString(entity)
        assertNotNull(serializedEntity)
    }
}