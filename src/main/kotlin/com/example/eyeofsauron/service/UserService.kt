package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.User
import com.example.eyeofsauron.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Сервис для работы с пользователями (сотрудниками) системы
 * @author rodkinsi
 */
@Service
class UserService(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun getAllUsers(): List<User> = repository.findAll()

    fun getUserById(id: Long) = repository.findById(id)

    fun getByUsernameAndPassword(username: String, password: String): User? =
        repository.findByUsername(username)?.run {
            if (passwordEncoder.matches(password, this.password)) return this else null
        }

    fun getByUsername(username: String) =
        repository.findByUsername(username)

    fun createUser(user: User) {
        user.password = passwordEncoder.encode(user.password)
        repository.save(user)
    }

    fun updateUser(user: User) = repository.save(user)

    fun delete(user: User) = repository.delete(user)

    fun deleteUserById(id: Long) = repository.deleteById(id)
}