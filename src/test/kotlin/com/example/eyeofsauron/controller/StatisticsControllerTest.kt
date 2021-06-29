package com.example.eyeofsauron.controller

import com.example.eyeofsauron.IntegrationTestBase
import com.example.eyeofsauron.TestUtil
import com.example.eyeofsauron.service.StatisticsService
import org.hibernate.stat.Statistics
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
class StatisticsControllerTest: IntegrationTestBase() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var statisticsController: StatisticsController

    @Autowired
    lateinit var markerController: MarkerController

    @Test
    fun getAll() {
        TestUtil.getAllTest(mockMvc,"/api/commits")
    }

    @Test
    fun getPlanned() {
        TestUtil.getAllTest(mockMvc,"/api/commits/planned")
    }

    @Test
    fun getUnplanned() {
        TestUtil.getAllTest(mockMvc,"/api/commits/unplanned")
    }

    @Test
    fun commitMarkerWhenCheckModeTrue() {
        statisticsController.switchMarkerCheckMode()
        statisticsController.commitMarker("commitImei", "commitRfid", 1)
        //новый маркер создается
        print(markerController.getAll())
    }

    @Test
    fun commitMarkerWhenCheckModeFalse() {
        statisticsController.commitMarker("commitImei", "commitRfid", 1)
        //новая запись статистики создается
        print(statisticsController.getAll())
    }
}