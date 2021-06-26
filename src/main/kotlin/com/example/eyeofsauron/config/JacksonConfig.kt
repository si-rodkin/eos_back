import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder




@Configuration
class JacksonConfig {
    @Bean
    fun jsonObjectMapper(): ObjectMapper? {
        return Jackson2ObjectMapperBuilder.json()
            .modules(JavaTimeModule())
            .build()
    }
}