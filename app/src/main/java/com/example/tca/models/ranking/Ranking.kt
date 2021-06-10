package com.example.tca.models.ranking


data class Ranking(
    val status: String,
    val message: String,
    val data: RankingData?
)

data class RankingData(
    val ranking: List<RankingFields>
)

data class RankingFields(
    val user: String,
    val score: Number
)
