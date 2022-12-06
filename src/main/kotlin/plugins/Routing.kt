package com.example.plugins

import com.example.models.Course
import com.example.models.Course.CourseFactory.getMostDifficultCourse
import com.example.models.Course.CourseFactory.searchCourseById
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.courseRouting() {

    /*Data init*/
    val courseStorage = Course.createCourses(10)

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
                return@get call.respondText(getMostDifficultCourse(courseStorage).toJson(), status = HttpStatusCode.OK)
            }
            get("/{id?}") {
                val id =
                    call.parameters["id"] ?: return@get call.respondText(
                        "Bad Request",
                        status = HttpStatusCode.BadRequest
                    )
                val course = searchCourseById(courseStorage, id.toInt())
                if (course is Course) return@get call.respondText(course.toJson(), status = HttpStatusCode.OK)
                else {
                    return@get call.respondText(
                        "Not Found: Course with _id: $id", status = HttpStatusCode.NotFound
                    )
                }
                //Best way but I don't really understand how it's works...
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
            get {
                call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
