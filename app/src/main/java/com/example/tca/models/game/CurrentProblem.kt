package com.example.tca.models.game

data class CurrentProblem(
    var status: String,
    var message: String,
    var data: NextProblemData?
)
