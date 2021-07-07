package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Employee
import com.example.eyeofsauron.entity.SecuredFacility
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class PermissionService(
    private val employeeService: EmployeeService,
    private val securedFacilityService: SecuredFacilityService
) {

    fun hasAccess(objectId: Long): Boolean {
        // Определяем текущего юзера
        // Получаем искомый объект
        // В цикле:
        // - проверяем, является ли он владельцем этого объекта
        // - если нет, то ищем владельца среди начальников либо пока не найдем
        //TODO: - если не нашли, ищем его среди подчиненных
        // Если владелец отсутствует, возвращаем false

        var employee: Employee = employeeService.getByUsername(currentEmployeeUsername)
        val securedFacility: SecuredFacility = securedFacilityService.getById(objectId).get()

        val rootEmployee = employee;
        return try {
            while (employee.id != securedFacility.owner.id) {
                val leaderId: Long? = employee.lead
                employee = leaderId?.let { employeeService.getById(it).get() }!!
            }
            true
        } catch (e: Exception) {
            try {
                while (rootEmployee.id != securedFacility.owner.id) {
                    rootEmployee.subordinates.forEach {
                        rootEmployee.id = it.id
                    }
                    Thread.sleep(1_000)
                }
                true
            } catch (e: Exception){
                println("Ebruh")
                false
            }
        }
    }

    companion object {
        //получаем при авторизации пользователя
        lateinit var currentEmployeeUsername: String
    }
}