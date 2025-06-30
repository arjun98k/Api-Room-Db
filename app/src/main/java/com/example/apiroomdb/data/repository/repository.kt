package com.example.apiroomdb.data.repository

import com.example.apiroomdb.data.remote.LoginRequest
import com.example.apiroomdb.data.remote.LoginResponse
import kotlinx.coroutines.delay
import retrofit2.Response

class LoginRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        // Simulate network delay
        delay(1000)

        return if (loginRequest.email == "test@example.com" && loginRequest.password == "123456") {
            // Mock success
            Response.success(
                LoginResponse(
                    userId = 1,
                    token = "mock_token_abc123",
                    fullName = "Test User"
                )
            )
        } else {
            // Mock failure
            Response.error(401, okhttp3.ResponseBody.create(null, "Unauthorized"))
        }
    }
}