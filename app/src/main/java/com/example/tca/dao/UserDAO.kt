package com.example.tca.dao

import android.util.Log
import com.example.tca.models.login.Login
import com.example.tca.models.login.User
import com.example.tca.models.register.Register
import com.example.tca.api.UsersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDAO {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(UsersService::class.java)

    fun createUser(user: User, finished: (body: Register) -> Unit) {
        service.insert(user).enqueue(object : Callback<Register> {
            override fun onResponse(call: Call<Register>, response: Response<Register>) {
                val user = response.body()
                Log.d("t", response.body().toString())
                finished(user!!)
            }
            override fun onFailure(call: Call<Register>, t: Throwable) {}
        })
    }

    fun login(email: String, password: String, finished: (body: Login) -> Unit, error: () -> Unit) {
        service.auth(email, password).enqueue(object : Callback<Login> {
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    finished(body)
                }else if(response.code() == 401){
                    error()
                }
            }
            override fun onFailure(call: Call<Login>, t: Throwable) {}
        })
    }
}