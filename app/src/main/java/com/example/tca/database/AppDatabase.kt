package com.example.tca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tca.database.daos.SessionDAO
import com.example.tca.database.daos.SessionGameDAO
import com.example.tca.models.DbSession
import com.example.tca.models.DbSessionGame
@Database(entities = [DbSession::class, DbSessionGame::class], version = 5)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sessionDao(): SessionDAO
    abstract fun sessionGameDao(): SessionGameDAO
}