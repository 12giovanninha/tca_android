package com.example.tca.models.game

data class CreateOrFindGame(
    var status: String,
    var message: String,
    var data: CreateOrFindGameData?
)

data class CreateOrFindGameData(
    val game: CreateOrFindGameFields
)

data class CreateOrFindGameFields(
    val creation: String,
    val status: String,
    val started_at: String,
    val score: Number
)

