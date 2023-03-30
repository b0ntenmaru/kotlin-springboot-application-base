package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringbootApplicationBaseApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringbootApplicationBaseApplication>(*args)
}
