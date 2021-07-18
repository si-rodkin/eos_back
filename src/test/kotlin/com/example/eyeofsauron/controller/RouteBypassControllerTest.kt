package com.example.eyeofsauron.controller

import com.example.eyeofsauron.EyeOfSauronApplication
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.RouteBypass
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import java.time.LocalTime
import java.time.format.DateTimeFormatter




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = arrayOf(EyeOfSauronApplication::class))
@AutoConfigureMockMvc
internal class RouteBypassControllerTest {
    var uri = RouteBypassController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var routeController: RouteController

    @Autowired
    lateinit var markerReaderController: MarkerReaderController

    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
    }

    @Test
    fun create() {
        val route = routeController.getById(1).get()
        val markerReader = markerReaderController.getById(1).get()
        val localTime = LocalTime.parse("14:34")

        val routeBypass = RouteBypass(9999, "rbName99", localTime, localTime,"3", null, null, false, route, markerReader)

        TestUtil.createTest(mockMvc, uri, routeBypass)
    }

    @Test
    fun update() {
        val route = routeController.getById(1).get()
        val markerReader = markerReaderController.getById(1).get()
        val localTime = LocalTime.parse("16:34")

        val routeBypass = RouteBypass(1, "newRbName1", localTime, localTime,"3", null, null, false, route, markerReader)

        TestUtil.updateTest(mockMvc, uri, routeBypass)
    }

    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "3")
    }
}