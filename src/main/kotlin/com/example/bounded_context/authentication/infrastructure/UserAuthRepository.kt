package com.example.bounded_context.authentication.infrastructure

import com.example.bounded_context.authentication.infrastructure.UserAuth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAuthRepository : JpaRepository<UserAuth, Long> {
    fun findByUsername(username: String): UserAuth?
}