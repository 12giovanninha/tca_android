package com.example.tca.api


import com.example.tca.models.category.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryService {
    @GET("categories")
    @Headers("Content-Type: application/json")
    fun findAll(): Call<Category>
}