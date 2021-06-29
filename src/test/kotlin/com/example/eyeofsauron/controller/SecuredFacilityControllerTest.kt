package com.example.eyeofsauron.controller

import org.junit.jupiter.api.Test
import com.example.eyeofsauron.EyeOfSauronApplication
import com.example.eyeofsauron.IntegrationTestBase
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.TestUtil.Companion.objectToJson
import com.example.eyeofsauron.entity.SecuredFacility
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.jvm.Throws

@AutoConfigureMockMvc
class SecuredFacilityControllerTest: IntegrationTestBase() {
    var uri = SecuredFacilityController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
            .andExpect(jsonPath("$.name").value("name1"))
            .andExpect(jsonPath("$.itn").value("itn1"))
    }

    @Test
    fun create() {
        val securedFacility = SecuredFacility(99999, "createName3", "createItn3")
        TestUtil.createTest(mockMvc, uri, securedFacility)
            .andExpect(jsonPath("$.name").value(securedFacility.name))
            .andExpect(jsonPath("$.itn").value(securedFacility.itn))
    }

    @Test
    fun update() {
        val securedFacility = SecuredFacility(1, "updateName1", "updateItn1")
        TestUtil.updateTest(mockMvc, uri, securedFacility)
    }

    @Test
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "2")
    }
}