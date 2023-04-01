package com.example.bounded_context.authentication.presentational

import com.example.bounded_context.authentication.infrastructure.UserAuth
import com.example.bounded_context.authentication.infrastructure.UserAuthRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAuthController(
    val userAuthRepository: UserAuthRepository,
    val passwordEncoder: PasswordEncoder
) {
    /**
     * デバッグ用エンドポイント
     */
    @GetMapping("/register")
    fun index(): List<UserAuth> {
        return userAuthRepository.findAll()
    }

    @PostMapping("/register")
    fun register(@RequestBody form: UserAuthForm): ResponseEntity<Any> {
        val existingUser = userAuthRepository.findByUsername(form.username)
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Username already exists.")
        }

        val userAuth = UserAuth(
            username = form.username,
            password = passwordEncoder.encode(form.password)
        )
        userAuthRepository.save(userAuth)

        return ResponseEntity.ok().build()
    }
}

data class UserAuthForm(
    val username: String,
    val password: String
)