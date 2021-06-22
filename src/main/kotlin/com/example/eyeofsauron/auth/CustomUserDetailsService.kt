package com.example.eyeofsauron.auth

import com.example.eyeofsauron.service.UserService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(private val userService: UserService): UserDetailsService {
    override fun loadUserByUsername(username: String) =
        CustomUserDetails.fromUserEntityToCustomUserDetails(userService.getByUsername(username))
}