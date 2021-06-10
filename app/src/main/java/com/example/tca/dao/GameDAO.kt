package com.example.tca.dao

import com.example.tca.models.game.*
import com.example.tca.api.GameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameDAO {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(GameService::class.java)

    fun findNextProblem(token: String, finished: (user: NextProblem) -> Unit, onError: (t: Throwable) -> Unit) {
        service.nextProblem(token).enqueue(object : Callback<NextProblem> {
            override fun onResponse(call: Call<NextProblem>, response: Response<NextProblem>) {
                if (response.isSuccessful) {
                    val game = response.body()!!
                    finished(response.body()!!)
                } else {
                    onError(Exception()) //callback 401
                }
            }
            override fun onFailure(call: Call<NextProblem>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun findOrCreate(difficulty: String?, categoryId: Number?, token: String, finished: (user: CreateOrFindGame) -> Unit, onError: (t: Throwable) -> Unit) {
        service.findOrCreate(difficulty, categoryId, token).enqueue(object : Callback<CreateOrFindGame> {
            override fun onResponse(call: Call<CreateOrFindGame>, response: Response<CreateOrFindGame>) {
                if (response.isSuccessful) {
                    finished(response.body()!!)
                }
            }
            override fun onFailure(call: Call<CreateOrFindGame>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun findCurrentProblem(token: String, finished: (user: CurrentProblem) -> Unit, onError: () -> Unit){
        service.currentProblem(token).enqueue(object : Callback<CurrentProblem> {
            override fun onResponse(call: Call<CurrentProblem>, response: Response<CurrentProblem>) {
                if (response.isSuccessful) {
                    finished(response.body()!!)
                } else if(response.code() == 400) {
                    onError()
                }
            }
            override fun onFailure(call: Call<CurrentProblem>, t: Throwable) {
                onError()
            }
        })
    }

    fun finishGame(token: String, finished: (user: FinishGame) -> Unit, onError: (t: Throwable) -> Unit) {
        service.finish(token).enqueue(object : Callback<FinishGame> {
            override fun onResponse(call: Call<FinishGame>, response: Response<FinishGame>) {
                if (response.isSuccessful) {
                    finished(response.body()!!)
                }
            }
            override fun onFailure(call: Call<FinishGame>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun sendAnswer(answerId: Number, token: String, finished: (user: SendAnswer) -> Unit, onError: (t: Throwable) -> Unit) {
        service.sendAnswer(answerId, token).enqueue(object : Callback<SendAnswer> {
            override fun onResponse(call: Call<SendAnswer>, response: Response<SendAnswer>) {
                if (response.isSuccessful) {
                    finished(response.body()!!)
                }
            }
            override fun onFailure(call: Call<SendAnswer>, t: Throwable) {
                onError(t)
            }
        })
    }


}