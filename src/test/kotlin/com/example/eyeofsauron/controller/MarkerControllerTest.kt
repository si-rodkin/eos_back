package com.example.eyeofsauron.controller

import com.example.eyeofsauron.EyeOfSauronApplication
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.entity.SecuredFacility
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.jvm.Throws

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = arrayOf(EyeOfSauronApplication::class))
@AutoConfigureMockMvc
internal class MarkerControllerTest {
    var uri = MarkerController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
    }

    @Test
    fun getFreeOrRouteMarkers() {
        TestUtil.getByIdTest(mockMvc, uri, "/free-or/1")
    }

    //Тест проходит, но объект не удаляется
    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "2")
    }
}