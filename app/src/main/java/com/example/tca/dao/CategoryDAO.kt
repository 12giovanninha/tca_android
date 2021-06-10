package com.example.tca.dao

import com.example.tca.models.category.Category
import com.example.tca.models.category.OutCategory
import com.example.tca.api.CategoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryDAO {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CategoryService::class.java)

    fun findAll(finished: (body: List<OutCategory>) -> Unit) {
        service.findAll().enqueue(object : Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                finished(response.body()?.data?.categories!!)
            }
            override fun onFailure(call: Call<Category>, t: Throwable) {}
        })
    }

}