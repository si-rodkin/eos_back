package com.example.eyeofsauron.controller

import com.example.eyeofsauron.EyeOfSauronApplication
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.entity.Route
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import kotlin.jvm.Throws

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = arrayOf(EyeOfSauronApplication::class))
@AutoConfigureMockMvc
internal class MarkerReaderControllerTest {
    var uri = MarkerReaderController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var routeController: RouteController

    @Test
    @Throws(Exception::class)
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
    }

    @Test
    fun create() {
        val route: List<Route> = routeController.getAll()
        val markerReader = MarkerReader(9999, "mrName9431", "5578853", "1488", route)

        TestUtil.createTest(mockMvc, uri, markerReader)
    }

    //Failed: 400 405
    @Test
    fun update() {
        val route = routeController.getById(1)

        TestUtil.updateTest(mockMvc, uri, route)
    }

    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "3")
    }
}