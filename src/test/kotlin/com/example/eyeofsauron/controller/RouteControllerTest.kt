package com.example.eyeofsauron.controller

import com.example.eyeofsauron.EyeOfSauronApplication
import com.example.eyeofsauron.IntegrationTestBase
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.entity.Marker
import com.example.eyeofsauron.entity.Route
import com.example.eyeofsauron.entity.SecuredFacility
import com.example.eyeofsauron.service.MarkerService
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*
import kotlin.jvm.Throws

@AutoConfigureMockMvc
internal class RouteControllerTest: IntegrationTestBase() {
    var uri = RouteController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var securedFacilityController: SecuredFacilityController

    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getByObject() {
        mockMvc.perform(MockMvcRequestBuilders.get(RouteController.uri + "/by-secured-facility/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"))
    }

    /*
    @Test
    fun create() {
        val securedFacility: SecuredFacility = securedFacilityController.getById(1).get()
        val route = Route(3, "createName3", securedFacility)

        TestUtil.createTest(mockMvc, uri, route)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(route.name))
    }

    @Test
    fun update() {
        val securedFacility: SecuredFacility = securedFacilityController.getById(1).get()
        val route = Route(1, "updateName1", securedFacility)

        TestUtil.updateTest(mockMvc, uri, route)
    }*/

    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "2")
    }
}