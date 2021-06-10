package com.example.tca.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.tca.R
import com.example.tca.adapters.RankingAdapter
import kotlinx.android.synthetic.main.fragment_ranking.view.*

class RankingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        view.progressBarRanking.visibility = View.VISIBLE
        view.containerFake.visibility = View.GONE
        view.listUsers.adapter = RankingAdapter(view)
        view.listUsers.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        return view
    }

}
