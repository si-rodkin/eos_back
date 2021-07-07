package com.example.eyeofsauron.auth

import com.example.eyeofsauron.service.EmployeeService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(private val employeeService: EmployeeService): UserDetailsService {
    override fun loadUserByUsername(username: String) =
        CustomUserDetails.fromUserEntityToCustomUserDetails(employeeService.getByUsername(username))
}