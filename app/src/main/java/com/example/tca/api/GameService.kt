package com.example.tca.api

import com.example.tca.models.game.*
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    @GET("games")
    @Headers("Content-Type: application/json")
    fun findOrCreate(
        @Query("difficulty") difficulty: String?,
        @Query("category_id") categoryId: Number?,
        @Header("Authorization") token: String
    ): Call<CreateOrFindGame>

    @DELETE("games")
    @Headers("Content-Type: application/json")
    fun finish(@Header("Authorization") token: String): Call<FinishGame>

    @GET("problems/next")
    @Headers("Content-Type: application/json")
    fun nextProblem(@Header("Authorization") token: String): Call<NextProblem>

    @GET("problems/view")
    @Headers("Content-Type: application/json")
    fun currentProblem(@Header("Authorization") token: String): Call<CurrentProblem>

    @POST("problems/answer")
    @Headers("Content-Type: application/json")
    fun sendAnswer(
        @Query("answer") answerId: Number,
        @Header("Authorization") token: String
    ): Call<SendAnswer>
}