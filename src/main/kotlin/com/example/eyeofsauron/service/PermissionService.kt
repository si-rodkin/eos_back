package com.example.eyeofsauron.service

import com.example.eyeofsauron.config.JwtProvider
import com.example.eyeofsauron.entity.CheckPoint
import com.example.eyeofsauron.entity.User
import com.example.eyeofsauron.entity.Ownerable
import com.mchange.rmi.NotAuthorizedException
import org.springframework.stereotype.Service

@Service
class PermissionService(
    private val userService: UserService,
    private val jwtProvider: JwtProvider
) {
    fun hasAccess(entity: Ownerable, token: String): Boolean {
        var user: User

        try {
            user = userService.getByUsername(jwtProvider.getLoginFromToken(token)!!)
        } catch (e: Exception) {
            throw NotAuthorizedException("Access denied!")
        }

        val rootUser = user;

        //Если текущий пользователь - владелец или админ
        //(у которого нет начальника), то даем доступ сразу
        if (user.id == entity.ownerId || user.lead == null)
            return true;

        while (user.lead != null) {
            user = userService.getById(user.lead!!).get()
            if (user.id == entity.ownerId)
                return true
        };

        return bottomSearch(rootUser, entity)
    }

    fun bottomSearch(rootUser: User, entity: Ownerable): Boolean {
        var viewedUser = rootUser

        while(viewedUser.subordinate?.isNotEmpty() == true){
            viewedUser.subordinate?.forEach {
                if(it.id == entity.ownerId)
                    return true
                viewedUser = it
            }
        }
        return false
    }
}