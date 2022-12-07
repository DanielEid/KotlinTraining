package com.example.plugins

import com.example.Controler.MongoControler
import com.example.models.Course
import com.example.models.Course.CourseFactory.getMostDifficultCourse
import com.example.models.Course.CourseFactory.searchCourseById
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.bson.Document

fun Application.courseRouting() {

    /*Data init*/
    val courseStorage = Course.createCourses(10)
    /*Data init mongodb*/
    val mongoControler = MongoControler()  //Comment for stop
    /*Add courses generate by courseStorage in the database, if their not present*/
    mongoControler.addCourse(courseStorage)

    routing {
        route("/") {
            get {
                call.respondText("Welcome to OC web server", status = HttpStatusCode.OK)
            }
        }
        route("/courses") {
            get {
                /*Data with var*/
                return@get call.respondText(courseStorage.toString(), status = HttpStatusCode.OK)
            }
        }
        route("/course") {
            post() {
                return@post call.respondText("Post works ... but not implemeted yet")
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
        /*With DataBase*/
        route("/dbcourses") {
            get {
                /*Data with MongoDB*/
                return@get call.respondText(mongoControler.getDocuments().toString(), status = HttpStatusCode.OK)
            }
        }
        route("/dbcourse") {
            post() {
                return@post call.respondText(call.parameters.toString())
            }
            get("/{id?}") {
                val id =
                    call.parameters["id"] ?: return@get call.respondText(
                        "Bad Request",
                        status = HttpStatusCode.BadRequest
                    )
                val course = mongoControler.getDocument(id.toInt())
                if (course is Document) return@get call.respondText(course.toJson(), status = HttpStatusCode.OK)
                else {
                    return@get call.respondText(
                        "Not Found: Course with _id: $id in the database", status = HttpStatusCode.NotFound
                    )
                }
            }
        }
        route("/*") {//Others cases
            get {
                call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
