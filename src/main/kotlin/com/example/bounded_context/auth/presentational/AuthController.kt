package com.example.bounded_context.auth.presentational

import com.example.bounded_context.auth.UserAuthService
import com.example.bounded_context.auth.UserDto
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.HashMap

@RestController
class AuthController(
    private val userAuthService: UserAuthService,
) {

    @GetMapping("api/jwt_sample")
    fun getJwt(): String {
        val jwt = generateJwt()
        return jwt;
    }

    @PostMapping("/register")
    fun register(@RequestBody userDto: UserDto): ResponseEntity<*> {
        val userAuth = userAuthService.createUser(userDto)
        val token = createToken(userAuth.username)
        val response = HashMap<String, String>()
        response["username"] = userAuth.username
        response["token"] = token
        return ResponseEntity.ok(response)
    }

    private fun createToken(username: String): String {
        val secretKey = "secret"
        val expiration = 8000
        val claims: Claims = Jwts.claims().setSubject(username)
//        claims["auth"] = role.authority
        val now = Date()
        val validity = Date(now.time + expiration)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    private fun generateJwt(): String {
        val claims = HashMap<String, Any?>()

        claims["aud"] = "doordash"
        claims["iss"] = "{developer_id}"
        claims["kid"] = "{key_id}"
        claims["exp"] = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(1).toEpochSecond()
        claims["iat"] = ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond()

        val key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256)

        return Jwts.builder()
            .setSubject("hiroaki")
            .setHeaderParam("dd-ver", "DD-JWT-V1")
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .signWith(key)
            .compact()
    }
}