package com.example.eyeofsauron.service

import com.example.eyeofsauron.config.JwtProvider
import com.example.eyeofsauron.entity.User
import com.example.eyeofsauron.entity.Ownerable
import org.springframework.stereotype.Service

@Service
class PermissionService(
    private val userService: UserService,
    private val jwtProvider: JwtProvider
) {
    fun hasAccess(objectId: Long, entity: Ownerable, token: String): Boolean {
        var user: User = userService.getByUsername(jwtProvider.getLoginFromToken(token)!!)
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
}