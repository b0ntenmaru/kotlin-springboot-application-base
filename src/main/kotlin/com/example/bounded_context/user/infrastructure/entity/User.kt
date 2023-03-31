package com.example.bounded_context.user.infrastructure.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id: Long? = null,

    @Column(
        name="name",
        nullable = false,
    )
    var name: String,

    @Column(
        name="email",
        nullable = false
    )
    var email: String
) {
    constructor(): this(null, "", "")
}

@Repository
interface JpaUserRepository: JpaRepository<User, Long> {
}
