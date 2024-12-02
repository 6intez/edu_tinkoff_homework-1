package com.example.edu_tinkoff_homework_1.data

data class JokeResponse(
    val jokes: List<ApiResponse>
)

data class ApiResponse(
    val id: Int,
    val category: String,
    val setup: String,
    val delivery: String
)