package com.example.bounded_context.user.presentation

import com.example.bounded_context.user.domain.User
import com.example.bounded_context.user.infrastructure.UserRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(val userRepository: UserRepository) {

    @GetMapping("/users")
    fun read(): List<User> {
        return userRepository.findAll()
    }

    @PostMapping("/users")
    fun create(@RequestBody request: CreateUserRequest) {
        userRepository.create(request.name, request.email)
        return
    }

    @PatchMapping("/users")
    fun update(@RequestBody request: UpdateUserRequest) {
        userRepository.update(request.id, request.name, request.email)
    }

    @DeleteMapping("/users")
    fun delete(@RequestBody request: DeleteUserRequest) {
        userRepository.delete(request.id)
        return
    }
}

data class CreateUserRequest(
    val name: String,
    val email: String
)

data class DeleteUserRequest(
    val id: Long
)

data class UpdateUserRequest(
    val id: Long,
    val name: String,
    val email: String
)