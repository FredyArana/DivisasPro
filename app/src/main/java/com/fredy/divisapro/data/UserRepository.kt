package com.fredy.divisapro.data

import com.fredy.divisapro.model.LoginRequest
import com.fredy.divisapro.model.LoginResponse
import com.fredy.divisapro.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun loginUser(email: String, password: String): LoginResponse? {
        val response = apiService.login(LoginRequest(email, password))
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}