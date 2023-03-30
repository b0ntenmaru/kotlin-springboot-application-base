package com.example.bounded_context.user.presentation

import com.example.bounded_context.user.domain.User
import com.example.bounded_context.user.infrastructure.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(val userRepository: UserRepository) {

    @GetMapping("/users")
    fun index(): List<User> {
        return userRepository.findAll()
    }
}