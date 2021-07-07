package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    fun findByUsername(username: String): Employee

    fun findAllByLeadingNotNull(): List<Employee>

    fun findByLead(leadId: Long): List<Employee>
}