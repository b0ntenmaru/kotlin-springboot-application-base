package com.example.bounded_context.user.infrastructure

import com.example.bounded_context.user.domain.User
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface UserRepository {
    fun create(name: String, email: String): Unit
    fun findAll(): List<User>
    fun update(id: Int, name: String, email: String): Unit
    fun delete(id: Int): Unit
}

@Repository
class UserRepositoryImpl(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): UserRepository {

    override fun create(name: String, email: String): Unit {
        val sql = """
            INSERT INTO
                users (name, email)
            VALUES (:name, :email);
        """.trimIndent()
        val sqlParams = MapSqlParameterSource()
            .addValue("name", name)
            .addValue("email", email)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }

    override fun findAll(): List<User> {
        val sql = "select * from users"
        val sqlParams = MapSqlParameterSource()
        val userMap = namedParameterJdbcTemplate.queryForList(sql, sqlParams)
        return userMap.map {
            User(
                it["id"].toString().toInt().toLong(),
                it["name"].toString(),
                it["email"].toString(),
            )
        }
    }

    override fun update(id: Int, name: String, email: String): Unit {
        val sql = """
            UPDATE users 
            SET 
                name = :name,
                email = :email 
            WHERE id = :id;
        """.trimIndent()

        val sqlParams = MapSqlParameterSource()
            .addValue("name", name)
            .addValue("email", email)
            .addValue("id", id)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }

    override fun delete(id: Int) {
        val sql = "delete from users where id = :id;"
        val sqlParams = MapSqlParameterSource()
            .addValue("id", id)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }
}