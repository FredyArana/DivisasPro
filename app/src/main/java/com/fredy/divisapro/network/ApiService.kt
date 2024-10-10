package com.fredy.divisapro.network

import com.fredy.divisapro.model.LoginRequest
import com.fredy.divisapro.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}