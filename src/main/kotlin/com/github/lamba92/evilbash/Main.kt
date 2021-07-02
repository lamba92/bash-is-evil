package com.github.lamba92.evilbash

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    embeddedServer(CIO, port = 8081) {
        routing {
            get("/") {
                println("Hello ${call.request.origin.host}!")
                call.respondText("Hello Kotland and world!")
            }
        }
    }.start()
}