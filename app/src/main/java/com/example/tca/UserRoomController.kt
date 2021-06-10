package com.example.tca

import android.app.Activity
import android.content.Context
import androidx.room.Room
import com.example.tca.database.AppDatabase
import com.example.tca.database.daos.SessionDAO
import com.example.tca.models.DbSession
import com.example.tca.models.login.User
import com.example.tca.models.login.LoginFields

class UserRoomController(context: Context) {

    companion object {
        var dao: SessionDAO? = null

        fun createDb(context: Context){
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user-db"
            )
                .allowMainThreadQueries()
                .build()
            dao = db.sessionDao()
        }

        fun exitUser(context: Context, id: Int) {
           dao?.delete(id)
        }

        fun setUser(context: Context, userFields: LoginFields) {
            dao?.insert(DbSession(userFields.email, userFields.name, userFields.token, 1))
        }

        fun isLog(activity: Activity): Boolean {
            return try {
                getUser(activity)
                true
            } catch (e: Exception) {
                false
            }
        }

        fun getUser(context: Context): User {
            val session = dao?.get()
            return User(session?.name!!, "", session.email!!, session.token!!)
        }
    }
}