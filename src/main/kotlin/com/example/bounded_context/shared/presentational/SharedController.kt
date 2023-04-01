package com.example.bounded_context.shared.presentational

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SharedController {
    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }
}