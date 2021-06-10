package com.example.tca.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class DbSession (
    val email: String?,
    val name: String?,
    val token: String?,
    val logado: Int?
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

