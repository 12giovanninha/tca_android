package com.example.tca.models.game

import com.example.tca.models.category.OutCategory

data class Game(
    var difficulty: String?,
    var outCategory: OutCategory?,
    var asserts: Int = 0,
    var errors: Int = 0,
    var score: Int = 0,
    var creation: String? = null,
    var status: String? = null,
    var startedAt: String? = null
)