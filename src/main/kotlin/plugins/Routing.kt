package com.example.plugins

import com.example.models.Course
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.util.reflect.*
import utils.OK


fun Application.courseRouting() {

    /*Data init*/
    val courseStorage = Course.createCourses(10)
    for (course in courseStorage) println(course._id)
    fun searchCourseById(id: String): Any {
        for (course in courseStorage)
            if (course._id == id) return course
        return false
    }

    routing {
        get("/") {
            call.respondText("${OK}: Welcome to OC web server")
        }
    }
    routing {
        get("/courses") {
            call.respondText { courseStorage.toString() }
        }
        get("/course") {
        }
        get("course/{id?}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            var course = searchCourseById(id);
            if (course is Course) call.respondText(course.toString())
            else {
                course = courseStorage.find {
                    it._id ==
                            id
                } ?: return@get call.respondText(
                    "Not Found", status = HttpStatusCode.NotFound
                )
            }

            call.respond(course)
        }
        /*get("course/{id?}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            val course = courseStorage.find { it._id == id } ?: return@get call.respondText(
                "Not Found", status = HttpStatusCode.NotFound
            )
            call.respond(course)
        }*/
    }
}
