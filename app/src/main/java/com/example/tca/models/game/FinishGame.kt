package com.example.tca.models.game

data class FinishGame(
    var status: String,
    var message: String,
    var data: FinishGameData?
)
data class FinishGameData(
    val game: FinishGameFields
)

data class FinishGameFields(
    val status: String,
    val started_at: String,
    val finished_at: String,
    val score: Number
)

