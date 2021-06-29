package com.example.eyeofsauron.controller

import com.example.eyeofsauron.IntegrationTestBase
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.Marker
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
class MarkerControllerTest: IntegrationTestBase() {
    var uri = MarkerController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var routeController: RouteController

    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.rfid").value("rfid1"))
    }

    @Test
    fun getFreeOrRouteMarkers() {
        TestUtil.getByIdTest(mockMvc, uri, "/free-or/1")
    }

    @Test
    fun update() {
        val route = routeController.getById(1).get()
        val marker = Marker(1, "updateName", "updateRfid", route)

        TestUtil.updateTest(mockMvc, uri, marker)
    }

    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "2")
    }
}