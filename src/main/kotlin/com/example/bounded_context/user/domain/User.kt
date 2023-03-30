package com.example.bounded_context.user.domain

data class User(
    val id: Int,
    val name: String,
    val email: String
)