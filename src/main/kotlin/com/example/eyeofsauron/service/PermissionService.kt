package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.User
import com.example.eyeofsauron.entity.Ownerable
import org.springframework.stereotype.Service

@Service
class PermissionService(
    private val userService: UserService,
) {
    fun hasAccess(objectId: Long, entity: Ownerable): Boolean {
        var user: User = userService.getByUsername(currentEmployeeUsername)
        val rootEmployee = user;

        //Если текущий пользователь - владелец или админ
        //(у которого нет начальника), то даем доступ сразу
        if (user.id == entity.ownerId || user.lead == null)
            return true;

        while (user.lead != null) {
            user = userService.getById(user.lead!!).get()
            if (user.id == entity.ownerId)
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