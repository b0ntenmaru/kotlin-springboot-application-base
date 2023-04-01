package com.example.bounded_context.authentication

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository

@Repository
interface UserAuthRepository : JpaRepository<UserAuth, Long> {
    fun findByUsername(username: String): UserAuth?
}