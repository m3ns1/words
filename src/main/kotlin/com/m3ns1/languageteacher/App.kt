package com.m3ns1.languageteacher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

@SpringBootApplication
class App