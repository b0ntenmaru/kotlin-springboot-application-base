package com.example.bounded_context.user.infrastructure

import com.example.bounded_context.user.domain.User
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface UserRepository {
    fun findAll(): List<User>
}

@Repository
class UserRepositoryImpl(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): UserRepository {

    override fun findAll(): List<User> {
        val sql = "select * from users"
        val sqlParams = MapSqlParameterSource()
        val userMap = namedParameterJdbcTemplate.queryForList(sql, sqlParams)
        return userMap.map {
            User(
                it["id"].toString().toInt().toLong(),
                it["name"].toString(),
                it["address"].toString(),
            )
        }
    }
}