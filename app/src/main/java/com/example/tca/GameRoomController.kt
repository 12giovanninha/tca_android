package com.example.tca

import android.content.Context
import androidx.room.Room
import com.example.tca.database.AppDatabase
import com.example.tca.database.daos.SessionGameDAO
import com.example.tca.models.DbSessionGame
import com.example.tca.models.category.OutCategory
import com.example.tca.models.game.Game

class GameRoomController(context: Context) {

    companion object {
        var dao: SessionGameDAO? = null

        fun createDb(context: Context){
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user-db"
            )
                .allowMainThreadQueries()
                .build()
            dao = db.sessionGameDao()
        }

        fun rollback(id: Int) {
            dao?.delete(id)
        }

        fun setGame(context: Context, game: Game) {
            val t = DbSessionGame(game.difficulty, game.outCategory.toString(),
                game.asserts, game.errors, game.score, game.outCategory?.name!!,
                game.outCategory?.id!!,  1)
            dao?.insert(t)
        }

        fun getGame(context: Context): Game {
            val session = dao?.get()
            val parse = OutCategory(session?.categoria!!, session?.num)
            return Game(session?.difficulty, parse, session.asserts, session.errors, session.score)
        }

        fun setScore(context: Context, score: Int) {
            val actually = getGame(context)
            actually.score = score
            rollback(1)
            setGame(context, actually)
        }

        fun addError(context: Context) {
            val actually = getGame(context)
            actually.errors = actually.errors + 1
            rollback(1)
            setGame(context, actually)
        }

        fun addAssert(context: Context) {
            val actually = getGame(context)
            actually.asserts = actually.asserts + 1
            rollback(1)
            setGame(context, actually)
        }
    }
}