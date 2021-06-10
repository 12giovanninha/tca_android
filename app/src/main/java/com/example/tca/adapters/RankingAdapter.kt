package com.example.tca.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tca.R
import com.example.tca.dao.RankingDAO
import com.example.tca.models.ranking.RankingFields
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*

class RankingAdapter(view: View): RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    private val dao: RankingDAO = RankingDAO()
    private var ranking: MutableList<RankingFields> = mutableListOf()
    private var viewAdapter = view
    private lateinit var pri: RankingFields
    private lateinit var seg: RankingFields
    private lateinit var ter: RankingFields

    init {
        dao.getRanking { rankingAPI ->
            ranking = rankingAPI.toMutableList()

            view.progressBarRanking.visibility = View.GONE

            pri = ranking[0]
            seg = ranking[1]
            ter = ranking[2]

            ranking.remove(ranking[2])
            ranking.remove(ranking[1])
            ranking.remove(ranking[0])

            viewAdapter.primeiro.text = pri.user.toUpperCase(locale = Locale.ENGLISH)
            viewAdapter.segundo.text = seg.user.toUpperCase(locale = Locale.ENGLISH)
            viewAdapter.terceiro.text = ter.user.toUpperCase(locale = Locale.ENGLISH)
            viewAdapter.score_first.text = pri.score.toString()
            viewAdapter.score_second.text = seg.score.toString()
            viewAdapter.score_trh.text = ter.score.toString()

            notifyDataSetChanged()

            view.containerFake.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return ranking.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fillView(ranking[position], position)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun fillView(user: RankingFields, position: Int){
            val pos = position + 4
            itemView.name.text = pos.toString() + "°   " + user.user.toUpperCase()
            itemView.score.text = user.score.toString()
        }
    }

}