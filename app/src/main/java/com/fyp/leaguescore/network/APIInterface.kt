package com.fyp.leaguescore.network

import com.fyp.leaguescore.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @POST("api/login")
    fun login(@Header("Authorization") authorization: String, @Body loginRequest: LoginRequest): Call<User>

    @POST("api/gamer")
    fun registerGamer(@Header("Authorization") authorization: String, @Body gamer: Gamer): Call<Gamer>

    @GET("api/predict")
    fun prediction(@Header("Authorization") authorization: String,@Query("ch1") ch1 : String,@Query("ch2")ch2 : String,@Query("ch3")ch3: String,@Query("ch4")ch4: String): Call<ArrayList<Team>>

    @GET("api/champion")
    fun getChampions(@Header("Authorization") authorization: String): Call<ArrayList<Champion>>

    @GET("api/video")
    fun getVideos(@Header("Authorization") authorization: String): Call<ArrayList<Video>>

    @GET("api/match")
    fun getMatches(@Header("Authorization") authorization: String): Call<ArrayList<Match>>

}