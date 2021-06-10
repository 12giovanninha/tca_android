package com.example.tca.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sessionGame")
data class DbSessionGame (
    var difficulty: String?,
    var category: String?,
    var asserts: Int = 0,
    var errors: Int = 0,
    var score: Int = 0,
    var categoria: String,
    var num: Int,
    var logado: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
