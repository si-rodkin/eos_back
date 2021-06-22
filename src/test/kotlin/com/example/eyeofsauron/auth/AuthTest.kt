package com.example.eyeofsauron.auth

import com.example.eyeofsauron.model.auth.Request
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.hamcrest.Matchers
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper

@SpringBootTest
@AutoConfigureMockMvc
class AuthTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun syncDateTest() {
        val dateNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"))
        val dayNow = LocalDateTime.now().dayOfWeek.value.toString()

        mockMvc.perform(get("/api/sync/date")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.time", Matchers.`is`(dateNow)))
            .andExpect(jsonPath("$.weekday", Matchers.`is`(dayNow)))
    }

    // TODO: написать утилитарный класс, принимающий пользовательские данные и генерящий токен
    // TODO:
    // TODO: написать фильтр, извлекающий токен из запроса и получающий по токену данные пользователя

    @Test
    fun generateUserTokenTest() {
        mockMvc.perform(post("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(Request("admin", "admin"))))
            .andExpect(status().isOk)
    }
}