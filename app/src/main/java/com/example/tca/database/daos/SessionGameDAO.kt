package com.example.tca.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tca.models.DbSessionGame

@Dao
interface SessionGameDAO {

    @Query("SELECT * FROM sessionGame")
    fun get(): DbSessionGame

    @Insert
    fun insert(user: DbSessionGame): Long

    @Query("DELETE FROM sessionGame Where logado IN (:ids)")
    fun delete(ids: Int)
}