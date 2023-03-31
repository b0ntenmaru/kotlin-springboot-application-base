package com.example.bounded_context.user.infrastructure

import com.example.bounded_context.user.domain.User
import com.example.bounded_context.user.infrastructure.entity.JpaUserRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import com.example.bounded_context.user.infrastructure.entity.User as EntityUser

interface UserRepository {
    fun create(name: String, email: String): Unit
    fun findAll(): List<User>
    fun update(id: Long, name: String, email: String): Unit
    fun delete(id: Long): Unit
}

@Repository
class UserRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val jpaUserRepository: JpaUserRepository
): UserRepository {

    override fun create(name: String, email: String): Unit {
        val user = EntityUser(null, name, email)
        jpaUserRepository.save(user)
        return
    }
    
    override fun findAll(): List<User> {
        val userRecords = jpaUserRepository.findAll()

        val domainUsers = userRecords.map { user ->
            User(
                id = user.id ?: throw IllegalStateException("User ID must not be null"),
                name = user.name,
                email = user.email
            )
        }
        return domainUsers
    }

    override fun update(id: Long, name: String, email: String): Unit {
        val user = EntityUser(id, name, email)
        jpaUserRepository.save(user)
        return
    }

    override fun delete(id: Long) {
        jpaUserRepository.deleteById(id)
        return
    }
}