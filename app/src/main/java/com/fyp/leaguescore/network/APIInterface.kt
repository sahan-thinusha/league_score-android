package com.fyp.leaguescore.network

import com.fyp.leaguescore.model.LoginRequest
import com.fyp.leaguescore.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface APIInterface {
    @POST("api/login")
    fun login(@Header("Authorization") authorization: String, @Body loginRequest: LoginRequest): Call<User>
}