package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Employee
import com.example.eyeofsauron.repository.EmployeeRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Сервис для работы с пользователями (сотрудниками) системы
 */
@Service
class EmployeeService(
    private val repository: EmployeeRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun getAll(uid: Long?): List<Employee> {
        return uid?.let {
            return repository.findByLead(it)
        } ?: repository.findAllByLeadingNotNull()
    }

    fun getById(id: Long) = repository.findById(id)

    fun getByUsernameAndPassword(username: String, password: String): Employee? =
        repository.findByUsername(username)?.run {
            if (passwordEncoder.matches(password, this.password)) return this else null
        }

    fun getByUsername(username: String) = repository.findByUsername(username)

    fun create(employee: Employee): Employee {
        employee.password = passwordEncoder.encode("123456")
        return repository.save(employee)
    }

    fun update(employee: Employee): Employee {
        if(!repository.existsById(employee.id))
            throw(Exception("Record not found!"))
        return repository.save(employee)
    }

    fun changePassword(employee: Employee): Employee {
        if (employee.password == employee.passwordRepeat) {
            employee.password = passwordEncoder.encode(employee.password)
            return repository.save(employee)
        }
        throw RuntimeException()
    }

    fun delete(employee: Employee) = repository.delete(employee)

    fun deleteById(id: Long) = repository.deleteById(id)
}