package com.example.eyeofsauron

import com.example.eyeofsauron.controller.SecuredFacilityController
import kotlin.Throws
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.lang.RuntimeException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class TestUtil {

    companion object {
        @JvmStatic
        @Throws(JsonProcessingException::class)
        fun objectToJson(obj: Any?): String {
            val objectMapper = ObjectMapper()
            objectMapper.findAndRegisterModules()

            return objectMapper.writeValueAsString(obj)
        }

        @JvmStatic
        @Throws(JsonProcessingException::class)
        fun putJson(uri: String?, body: Any?): MockHttpServletRequestBuilder {
            return try {
                val json = ObjectMapper().writeValueAsString(body)
                MockMvcRequestBuilders.put(uri!!)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            } catch (e: JsonProcessingException) {
                throw RuntimeException(e)
            }
        }

        @JvmStatic
        fun getAllTest(mockMvc: MockMvc, uri: String): ResultActions{
            return mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
        }

        @JvmStatic
        fun getByIdTest(mockMvc: MockMvc, uri: String, id: String): ResultActions{
            return mockMvc.perform(MockMvcRequestBuilders.get("$uri/$id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
        }

        @JvmStatic
        fun createTest(mockMvc: MockMvc, uri: String, obj: Any): ResultActions{
            return mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectToJson(obj)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andDo(MockMvcResultHandlers.print())
        }

        @JvmStatic
        fun updateTest(mockMvc: MockMvc, uri: String, obj: Any): ResultActions{
            return mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(obj)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
        }

        @JvmStatic
        fun deleteByIdTest(mockMvc: MockMvc, uri: String, id: String): ResultActions{
            return mockMvc.perform(MockMvcRequestBuilders.delete("$uri/$id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent)
                .andDo(MockMvcResultHandlers.print())
        }
    }



    //public static ResultMatcher product(String prefix, Product product) {
    //    return ResultMatcher.matchAll(
    //            jsonPath(prefix + ".name").value(product.getName()),
    //            jsonPath(prefix + ".description").value(product.getDescription())
    //    );}
    fun noCacheHeader(): ResultMatcher {
        return MockMvcResultMatchers.header().string("Cache-Control", "no-cache")
    }
}