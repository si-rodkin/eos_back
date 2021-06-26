package com.example.eyeofsauron.controller

import com.example.eyeofsauron.EyeOfSauronApplication
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.entity.RouteBypass
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import java.time.LocalTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = arrayOf(EyeOfSauronApplication::class))
@AutoConfigureMockMvc
internal class CheckPointControllerTest {
    var uri = CheckPointController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var routeBypassController: RouteBypassController

    @Autowired
    lateinit var markerController: MarkerController

    //Error Status 500
    //TODO че-то не то с timestamp, исправить
    //Local Time? <---> integer
    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
    }

    @Test
    fun getByBypass() {
    }

    @Test
    fun create() {
        val routeBypass = routeBypassController.getById(1).get()
        val marker = markerController.getById(1).get()
        val checkPoint = CheckPoint(9999, "cp9999", LocalTime.now(), 1, 4, routeBypass, marker)

        TestUtil.createTest(mockMvc, uri, checkPoint)
    }

    @Test
    fun update() {
        val routeBypass = routeBypassController.getById(1).get()
        val marker = markerController.getById(1).get()
        val checkPoint = CheckPoint(1, "cp9sadfv", LocalTime.now(), 1, 4, routeBypass, marker)

        TestUtil.updateTest(mockMvc, uri, checkPoint)
    }

    @Test
    fun deleteById() {
    }
}