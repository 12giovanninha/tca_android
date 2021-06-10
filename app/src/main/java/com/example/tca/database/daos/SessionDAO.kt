package com.example.tca.database.daos

import androidx.room.*
import com.example.tca.models.DbSession

@Dao
interface SessionDAO {

    @Query("SELECT * FROM session")
    fun getAll(): List<DbSession>

    @Query("SELECT * FROM session")
    fun get(): DbSession

    @Query("SELECT * FROM session WHERE id IN (:ids)")
    fun getAllByIds(ids: IntArray): List<DbSession>

    @Query("SELECT * FROM session WHERE name LIKE :name AND email LIKE :email")
    fun findByName(name: String, email: String): DbSession

    @Insert
    fun insert(user: DbSession): Long

    @Query("DELETE FROM session Where logado IN (:ids)")
    fun delete(ids: Int)
}