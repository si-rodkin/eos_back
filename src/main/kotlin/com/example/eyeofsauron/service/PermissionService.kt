package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Employee
import com.example.eyeofsauron.entity.IEntity
import com.example.eyeofsauron.entity.SecuredFacility
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class PermissionService(
    private val employeeService: EmployeeService,
) {
    fun hasAccess(objectId: Long, entity: IEntity): Boolean {
        var employee: Employee = employeeService.getByUsername(currentEmployeeUsername)
        val rootEmployee = employee;

        //Если текущий пользователь - владелец или админ
        //(у которого нет начальника), то даем доступ сразу
        if (employee.id == entity.ownerId || employee.lead == null)
            return true;

        while (employee.lead != null) {
            employee = employeeService.getById(employee.lead!!).get()
            if (employee.id == entity.ownerId)
                return true
        };
        rootEmployee.subordinate?.forEach {
            rootEmployee.id = it.id
            if (rootEmployee.id == entity.ownerId)
                return true
        }
        return false
    }

    companion object {
        //получаем при авторизации пользователя
        lateinit var currentEmployeeUsername: String
    }
}