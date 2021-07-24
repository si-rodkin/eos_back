package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun findByUsername(username: String): User

    fun findAllByLeadingNotNull(): List<User>

    fun findByLead(leadId: Long): List<User>
}