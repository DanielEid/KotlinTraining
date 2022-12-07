package com.example.Controler

import com.example.models.Course
import com.mongodb.MongoClient
import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoCursor
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.bson.Document
import utils.*

class MongoControler {

    lateinit var mongoClient: MongoClient
    lateinit var mongoDatabase: MongoDatabase
    lateinit var mongoCollection: MongoCollection<Document>

    init {
        try {
            mongoClient = MongoClient(MONGO_URI, MONGO_PORT)
            println("Connected to mongo at $MONGO_URI");

            /*Get database*/
            mongoDatabase = mongoClient!!.getDatabase(MONGO_DATABASE)
            /*Get collection*/
            mongoCollection = mongoDatabase.getCollection(MONGO_COLLECTION)
        } catch (e: MongoException) {
            e.printStackTrace()
        } finally {
        }
    }


    fun getDocuments(): MutableList<String> {
        var documentsInCollection: MutableList<String> = mutableListOf()

        val cursor: MongoCursor<Document> = mongoCollection.find().iterator()
        try {
            while (cursor.hasNext()) {
                documentsInCollection.add(cursor.next().toJson())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.close()
        }
        return documentsInCollection
    }

    fun getDocument(course_Id: Int) = mongoCollection.find(Filters.eq("_id", course_Id)).first()

    fun addCourse(course: Course): Boolean {   /*Get all document in the collection*/

        if (getDocument(course._id) == null) {
            val document: Document = Document();

            document.append("_id", course._id)
                .append("title", course.title)
                .append("content", course.content)
                .append("level", course.level)
                .append("isActive", course.isActive)

            mongoCollection.insertOne(document);
            return true
        }
        return false
    }

    fun addCourse(courseList: MutableList<Course>){
        for (course in courseList) {
            val result= this.addCourse(course)
            logMessage("The course _id: ${course._id} ,title: ${course.title} is add to database?: $result")
        }
    }
}

