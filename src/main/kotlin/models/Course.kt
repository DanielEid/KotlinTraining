package com.example.models

import com.google.gson.Gson
import kotlin.random.Random

data class Course(val _id: Int, val title: String, val content: String, val level: Int, var isActive: Boolean) {

fun toJson()= "{\"_id:\"${this._id},\"title\":\"${this.title}\",\"content\":\"${this.content}\",\"level\":${this.level},\"isActive\":$isActive}"

    companion object CourseFactory {
        fun createCourses(number: Int): MutableList<Course> {
            val listOfCourse = mutableListOf<Course>();
            for (i in 0..number) {
                listOfCourse.add(Course(i, "Title", "content", Random.nextInt(0, 6), true))
            }
            return listOfCourse
        }

        fun searchCourseById(courseStorage: MutableList<Course>, id: Int): Any {
            for (course in courseStorage)
                if (course._id == id) return course
            return false
        }

        fun getMostDifficultCourse(courseStorage : MutableList<Course>): Course {
            var mostDifficultCourse: Course = courseStorage[0]

            for (course in courseStorage) {
                if (course.level > mostDifficultCourse.level) mostDifficultCourse =
                    course //If course as same level, it's ignored (because not indicate in the train)
            }
            return mostDifficultCourse
        }
    }
}