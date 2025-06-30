package com.example.apiroomdb.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import com.example.apiroomdb.data.remote.LoginResponse

interface ApiService {

    @POST("login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    // You can add other mockable API endpoints here
}