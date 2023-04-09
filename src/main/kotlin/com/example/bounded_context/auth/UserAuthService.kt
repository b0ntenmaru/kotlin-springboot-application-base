package com.example.bounded_context.auth

import com.example.bounded_context.auth.infrastructure.UserAuth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class UserAuthService(
    private val userAuthRepository: UserAuthRepository,
) {

    fun createUser(userDto: UserDto): UserAuth {
        val user = UserAuth(
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password)
        )
        return userAuthRepository.save(user)
    }

    companion object {
        private val passwordEncoder = BCryptPasswordEncoder()
    }
}

data class UserDto(
    val username: String,
    val password: String
)

@Repository
interface UserAuthRepository : JpaRepository<UserAuth, Long>