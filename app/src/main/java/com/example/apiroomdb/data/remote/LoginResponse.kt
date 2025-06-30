package com.example.apiroomdb.data.remote


data class LoginResponse(
    val userId: Int,
    val token: String,
    val fullName: String
)