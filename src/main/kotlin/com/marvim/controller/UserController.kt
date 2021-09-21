package com.marvim.controller

import com.marvim.model.User
import com.marvim.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller("/user")
@Secured(SecurityRule.IS_ANONYMOUS)
class UserController(private val userService: UserService) {

    @Post
    fun addUser(user: User): HttpResponse<User>{
        return HttpResponse.created(userService.addUser(user))
    }
}