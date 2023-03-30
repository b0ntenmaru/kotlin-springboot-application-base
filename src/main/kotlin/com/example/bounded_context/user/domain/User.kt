package com.example.bounded_context.user.domain

data class User(
    val id: Long,
    val name: String,
    val email: String
)