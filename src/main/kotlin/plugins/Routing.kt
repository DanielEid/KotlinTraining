package com.example.plugins

import com.example.models.Course
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.courseRouting() {

    /*Data init*/
    val courseStorage = Course.createCourses(10)
    for (course in courseStorage) println(course._id)
    fun searchCourseById(id: Int): Any {
        for (course in courseStorage)
            if (course._id == id) return course
        return false
    }

    fun getMostDifficultCourse(): Course {
        var mostDifficultCourse: Course = courseStorage[0]

        for (course in courseStorage) {
            if (course.level > mostDifficultCourse.level) mostDifficultCourse =
                course //If course as same level, it's ignored (because not indicate in the train)
        }
        return mostDifficultCourse
    }

    routing {
        route("/") {
            get {
                call.respondText("Welcome to OC web server", status = HttpStatusCode.OK)
            }
        }
        route("/courses") {
            get {
                return@get call.respondText(courseStorage.toString(), status = HttpStatusCode.OK)

            }
        }
        route("/course") {
            post() {
                return@post call.respondText("ca marche le post ")
            }
            get("/top") {
                return@get call.respondText(getMostDifficultCourse().toString(), status = HttpStatusCode.OK)
            }
            get("/{id?}") {
                val id =
                    call.parameters["id"] ?: return@get call.respondText(
                        "Bad Request",
                        status = HttpStatusCode.BadRequest
                    )
                val course = searchCourseById(id.toInt())
                if (course is Course) return@get call.respondText(course.toString(), status = HttpStatusCode.OK)
                else {
                    return@get call.respondText(
                        "Not Found", status = HttpStatusCode.NotFound
                    )
                }
                //Best way but I don't really understood how it's works...
                /* else {
                    course = courseStorage.find {
                        it._id ==
                                id
                    } ?: return@get call.respondText(
                        "Not Found", status = HttpStatusCode.NotFound
                    )
                }*/

            }
        }
        route("/*") {//Others cases
            get{
                call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            }
        }

        }
}
