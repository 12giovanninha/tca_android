package com.example.tca.api

import com.example.tca.models.login.Login
import com.example.tca.models.login.User
import com.example.tca.models.register.Register

import retrofit2.Call
import retrofit2.http.*

interface UsersService {

    @POST("users")
    @Headers("Content-Type: Application/json")
    fun insert(@Body user: User): Call<Register>

    @POST("auth")
    @Headers("Content-Type: application/json")
    fun auth(@Query("email") username: String, @Query("password") password: String): Call<Login>

}