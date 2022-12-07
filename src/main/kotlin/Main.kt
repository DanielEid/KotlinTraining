package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import java.net.BindException

fun main() {

    /*Server*/
    try {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
    }
    catch (e: BindException){
        println(e.message)
    }
}

fun Application.module() {
    /*Routing*/
    courseRouting()
}
