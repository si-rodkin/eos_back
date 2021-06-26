package com.example.eyeofsauron.controller

import org.junit.jupiter.api.Test
import com.example.eyeofsauron.EyeOfSauronApplication
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.jvm.Throws


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = arrayOf(EyeOfSauronApplication::class))
@AutoConfigureMockMvc
class SecuredFacilityControllerTest {
    var uri = SecuredFacilityController.uri

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @Throws(Exception::class)
    fun getAll() {
        TestUtil.getAllTest(mockMvc, uri)
    }

    @Test
    @Throws(Exception::class)
    fun getById() {
        TestUtil.getByIdTest(mockMvc, uri, "1")
    }

    @Test
    @Throws(Exception::class)
    fun create() {
        val securedFacility = SecuredFacility(99999, "lmaoo", "kekk")
        TestUtil.createTest(mockMvc, uri, securedFacility)
    }

    @Test
    @Throws(Exception::class)
    fun update() {
        val securedFacility = SecuredFacility(1, "4451s", "14125")
        TestUtil.updateTest(mockMvc, uri, securedFacility)
    }

    @Test
    @Throws(Exception::class)
    fun deleteById() {
        TestUtil.deleteByIdTest(mockMvc, uri, "6")
    }
}