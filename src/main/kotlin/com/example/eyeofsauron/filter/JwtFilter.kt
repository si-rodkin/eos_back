package com.example.eyeofsauron.filter

import com.example.eyeofsauron.auth.CustomUserDetailsService
import com.example.eyeofsauron.config.JwtProvider
import io.jsonwebtoken.lang.Strings.hasText
import jdk.jshell.spi.ExecutionControl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.lang.RuntimeException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtFilter(
    private val jwtProvider: JwtProvider,
    private val customUserDetailsService: CustomUserDetailsService
) : GenericFilterBean() {
    val AUTHORIZATION = "Authorization"

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse?, filterChain: FilterChain) {
//        logger.info("do filter...")
//        val token = getTokenFromRequest(servletRequest as HttpServletRequest)
//        if (token != null && jwtProvider.validateToken(token)) {
//            val userLogin = jwtProvider.getLoginFromToken(token)
//            val customUserDetails = customUserDetailsService.loadUserByUsername(userLogin!!)
//            val auth = UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails!!.authorities)
//            SecurityContextHolder.getContext().authentication = auth
//        }
//        // TODO: Unauthorized
//        throw RuntimeException()
        filterChain.doFilter(servletRequest, servletResponse)

    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearer = request.getHeader(AUTHORIZATION)
        return if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else null
    }
}