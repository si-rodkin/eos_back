package com.example.eyeofsauron

import com.example.eyeofsauron.initializer.Postgres
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@Sql("/db/dataset.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = arrayOf(EyeOfSauronApplication::class))
@ContextConfiguration(initializers = [Postgres.Companion.Initializer::class])
@Transactional
abstract class IntegrationTestBase {
    companion object {
        @BeforeAll
        fun init() {
            Postgres.container.start()
        }
    }
}