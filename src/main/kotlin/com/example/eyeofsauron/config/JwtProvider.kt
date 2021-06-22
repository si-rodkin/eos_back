package com.example.eyeofsauron.config

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Configuration
class JwtProvider(
    @Value("$(jwt.secret)")
    val jwtSecret: String
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun generateToken(login: String?): String {
        val date: Date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
            .setSubject(login)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (expEx: ExpiredJwtException) {
//            log.severe("Token expired")
        } catch (unsEx: UnsupportedJwtException) {
//            log.severe("Unsupported jwt")
        } catch (mjEx: MalformedJwtException) {
//            log.severe("Malformed jwt")
        } catch (sEx: SignatureException) {
//            log.severe("Invalid signature")
        } catch (e: Exception) {
//            log.severe("invalid token")
        }
        return false
    }

    fun getLoginFromToken(token: String?): String? {
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        return claims.subject
    }
}