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
    fun getAllUsers(uid: Long?): List<User> {
        return uid?.let {
            return repository.findByLead(it)
        } ?: repository.findAllByLeadingNotNull()
    }

    fun getUserById(id: Long) = repository.findById(id)

    fun getByUsernameAndPassword(username: String, password: String): User? =
        repository.findByUsername(username)?.run {
            if (passwordEncoder.matches(password, this.password)) return this else null
        }

    fun getByUsername(username: String) =
        repository.findByUsername(username)

    fun createUser(user: User): User {
        user.password = passwordEncoder.encode("123456")
        return repository.save(user)
    }

    fun updateUser(user: User): User {
        user.password = repository.getById(user.id).password
        return repository.save(user)
    }

    fun changePassword(user: User): User {
        if (user.password == user.passwordRepeat) {
            user.password = passwordEncoder.encode(user.password)
            return repository.save(user)
        }
        throw RuntimeException()
    }

    fun delete(user: User) = repository.delete(user)

    fun deleteUserById(id: Long) = repository.deleteById(id)
}