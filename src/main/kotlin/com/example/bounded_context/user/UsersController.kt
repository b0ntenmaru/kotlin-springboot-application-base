package com.example.bounded_context.user

import com.example.bounded_context.user.domain.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController {

    @GetMapping("/users")
    fun index(): List<User> {
        val user = User(1, "hiroaki date", "example@email.com")

        return listOf(user)
    }
}