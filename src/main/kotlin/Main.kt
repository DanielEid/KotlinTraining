package com.example

import com.example.models.Course
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import java.net.BindException

fun main() {
    try {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
    }
    catch (e: BindException){
        println(e.message)
    }
    var course : Course = Course("0","title","content",1,true)


}

fun Application.module() {

    /*Data init*/
   // val courseStorage = Course.createCourses(10)
    //for (course in courseStorage) println(course.level)

    /*Routing*/
    courseRouting()


}
