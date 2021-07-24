package com.example.eyeofsauron.controller

import com.example.eyeofsauron.IntegrationTestBase
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.CheckPoint
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalTime

@AutoConfigureMockMvc
internal class CheckPointControllerTest: IntegrationTestBase() {
    var uri = CheckPointController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var routeBypassController: RouteBypassController

    @Autowired
    lateinit var markerController: MarkerController

    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.readTime").value("11:11"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.allowanceTime").value(11))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lateTime").value(11))
    }

    //TODO исправить ошибку: возвращает 200 при запросе несуществующего bypass
    @Test
    fun getByBypass() {
        TestUtil.getByIdTest(mockMvc, uri, "/by-routebypass/1")
    }

    @Test
    fun create() {
        val routeBypass = routeBypassController.getById(1).get()
        val marker = markerController.getById(1).get()
        val readTime = LocalTime.parse("21:12")
        val checkPoint = CheckPoint(9999, "createName3", readTime, 1, 4, routeBypass, marker)

        TestUtil.createTest(mockMvc, uri, checkPoint)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(checkPoint.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.readTime").value(checkPoint.readTime.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.allowanceTime").value(checkPoint.allowanceTime))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lateTime").value(checkPoint.lateTime))
    }

    @Test
    fun update() {
        val routeBypass = routeBypassController.getById(1).get()
        val marker = markerController.getById(1).get()
        val checkPoint = CheckPoint(1, "updateName3", LocalTime.now(), 3, 6, routeBypass, marker)

        TestUtil.updateTest(mockMvc, uri, checkPoint)
    }

    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "1")
    }
}