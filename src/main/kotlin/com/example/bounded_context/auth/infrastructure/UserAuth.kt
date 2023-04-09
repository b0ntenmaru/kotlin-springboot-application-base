package com.example.bounded_context.auth.infrastructure

import javax.persistence.*

@Entity
@Table(name="user_auth")
data class UserAuth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long? = null,

    @Column(
        name="username",
        nullable = false,
    )
    val username: String,

    @Column(
        name="password",
        nullable = false
    )
    val password: String,
)