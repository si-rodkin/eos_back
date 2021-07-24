package com.example.eyeofsauron.initializer

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer

class Postgres {
    companion object {
        val container: PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>("postgres:13.2")

        class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(applicationContext: ConfigurableApplicationContext) {
                TestPropertyValues.of(
                    "spring.datasource.url=jdbc:postgresql://localhost:5432/suro_dev_db",
                    "spring.datasource.username=postgres",
                    "spring.datasource.password=879587321rgk"

                    //TODO заменить хардкод пропертей на:
                    //"spring.datasource.url=" + container.jdbcUrl,
                    //"spring.datasource.username=" + container.username,
                    //"spring.datasource.password=" + container.password
                ).applyTo(applicationContext)
            }
        }
    }
}