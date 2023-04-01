package com.example.bounded_context.authentication

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class UserAuth(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    var password: String,
    val token: String? = null
) {
    constructor() : this(null, "", "", "") {

    }
}