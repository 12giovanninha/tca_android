package com.example.tca.dao

import com.example.tca.models.ranking.Ranking
import com.example.tca.models.ranking.RankingFields
import com.example.tca.api.RankingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RankingDAO {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(RankingService::class.java)

    fun getRanking(finished: (user: List<RankingFields>) -> Unit) {
        service.getAll().enqueue(object : Callback<Ranking> {
            override fun onResponse(call: Call<Ranking>, response: Response<Ranking>) {
                val users = response.body()!!.data?.ranking
                if (users != null) { finished(users) }
            }
            override fun onFailure(call: Call<Ranking>, t: Throwable) {}
        })
    }
}
