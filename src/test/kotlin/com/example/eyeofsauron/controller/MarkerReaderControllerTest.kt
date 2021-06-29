package com.example.eyeofsauron.controller

import com.example.eyeofsauron.IntegrationTestBase
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.MarkerReader
import com.example.eyeofsauron.entity.Route
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.jvm.Throws

@AutoConfigureMockMvc
internal class MarkerReaderControllerTest: IntegrationTestBase() {
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
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.imei").value("imei1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("phone1"))
    }

    @Test
    fun create() {
        val routeList: List<Route> = routeController.getAll()
        val markerReader = MarkerReader(9999, "createName3", "createImei3", "createPhone3", routeList)

        TestUtil.createTest(mockMvc, uri, markerReader)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(markerReader.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.imei").value(markerReader.imei))
            .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(markerReader.phone))
    }

    @Test
    fun update() {
        val routeList: List<Route> = routeController.getAll()
        val markerReader = MarkerReader(1, "updateName1", "updateImei1", "updateImei1", routeList)

        TestUtil.updateTest(mockMvc, uri, markerReader)
    }

    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "2")
    }
}