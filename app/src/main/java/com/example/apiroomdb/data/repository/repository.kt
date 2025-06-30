package com.example.apiroomdb.data.repository

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.apiroomdb.data.remote.LoginRequest
import com.example.apiroomdb.data.remote.LoginResponse
import kotlinx.coroutines.delay
import retrofit2.Response
import okhttp3.ResponseBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import androidx.core.content.ContentProviderCompat.requireContext


class LoginRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        delay(100) // Simulate network delay

        return if (loginRequest.email == "test@example.com" && loginRequest.password == "123456") {


            // ✅ Mock success
            Response.success(
                LoginResponse(
                    userId = 1,
                    token = "mock_token_abc123",
                    fullName = "Test User"
                )
            )
        } else {
            // ❌ Mock failure
            Response.error(
                401,
                ResponseBody.create("application/json".toMediaTypeOrNull(), "{\"error\":\"Unauthorized\"}")
            )
        }
    }
}
