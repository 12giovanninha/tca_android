package com.example.tca.models.game

import com.example.tca.models.problem.Answer
import com.google.gson.annotations.SerializedName

data class SendAnswer(
    var status: String,
    var message: String,
    var data: SendAnswerData
)

data class SendAnswerData(
    var answer: SendAnswerFields
)

data class SendAnswerFields(
    var status: String,
    var score: Int,
    @SerializedName("correct_answer")
    var correctAnswer: Answer
) {
    fun isCorrect() : Boolean {
        return status != "incorrect"
    }
}
