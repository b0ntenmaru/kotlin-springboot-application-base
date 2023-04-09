//package com.example.bounded_context.auth
//
//import io.jsonwebtoken.Claims
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.SignatureAlgorithm
//import org.springframework.stereotype.Component
//import java.util.*
//
//@Component
//class JwtTokenProvider(
//    private val secretKey: String,
//    private val expiration: Long
//) {
//
//    /**
//     * Roleいらないかも
//     */
//    fun createToken(username: String): String {
//        val claims: Claims = Jwts.claims().setSubject(username)
////        claims["auth"] = role.authority
//        val now = Date()
//        val validity = Date(now.time + expiration)
//        return Jwts.builder()
//            .setClaims(claims)
//            .setIssuedAt(now)
//            .setExpiration(validity)
//            .signWith(SignatureAlgorithm.HS256, secretKey)
//            .compact()
//    }
//}