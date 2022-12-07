package com.example

import com.example.models.Course
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.mongodb.MongoClient
import com.mongodb.MongoException
import com.mongodb.client.MongoCursor
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import java.net.BindException

fun main() {

    var mongoClient: MongoClient? = null
    try {
        mongoClient = MongoClient("localhost:27017", 27017)
        println("${mongoClient.toString()} Connected to mongo")
        //for (db in mongoClient.listDatabases()) println(db.toJson())
        val database: MongoDatabase = mongoClient.getDatabase("test")
        val courseCollection = database.getCollection("course")


        println(courseCollection.countDocuments())


        /*Get all document in the collection*/
        val cursor: MongoCursor<Document> = courseCollection.find().iterator()
        try {
            while (cursor.hasNext()) {
                println(cursor.next().toJson());
            }
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        finally {
            cursor.close()
        }
        /*Get one document by is id*/
        val myCourse = courseCollection.find(eq("_id",99)).first()
        println("myCourse find: $myCourse")

        var document: Document = Document()
        var course = Course(999, "title99", "content99", 1, false)
        document.apply {
            document.append("_id", course._id)
                .append("title", course.title)
                .append("content", course.content)
                .append("level", course.level)
                .append("isActive", course.isActive)
        }
        // println(document.toJson())
        //courseCollection.insertOne(document)


    } catch (e: MongoException) {
        e.printStackTrace()
    } finally {
        mongoClient!!.close()
    }
    /*try {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
    }
    catch (e: BindException){
        println(e.message)
    }*/
}

fun Application.module() {
    /*Routing*/
    courseRouting()
}
