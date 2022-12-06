package com.example.models

import kotlin.random.Random

data class Course(val _id: String, val title: String, val content: String, val level: Int, var isActive: Boolean) {

    companion object CourseFactory {
        fun createCourses(number: Int): MutableList<Course> {
            val listOfCourse = mutableListOf<Course>();
            for (i in 0..number) {
                listOfCourse.add(Course("$i", "Title", "content", Random.nextInt(0, 6), true))
            }
            return listOfCourse
        }
    }
}