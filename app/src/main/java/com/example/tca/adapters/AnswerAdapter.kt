package com.example.tca.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tca.R
import com.example.tca.models.problem.Answer
import kotlinx.android.synthetic.main.item_answer.view.*

class AnswerAdapter(answersList: List<Answer>) : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    private var correct : Answer? = null
    private var error: Answer? = null
    private var answers = mutableListOf<Answer>()
    private var selected : Answer? = null

    init {
        answers = answersList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = answers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.fillView(answers[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor")
        fun fillView(answer: Answer, position: Int) {
            itemView.tvAnswer.text = answer.order.toString() + ") " + answer.description

            when (answer) {
                correct -> {
                    itemView.setBackgroundColor(Color.parseColor("#FF3FE375"))
                    itemView.tvAnswer.setTextColor(Color.parseColor("#ffffff"))
                }
                error -> {
                    itemView.setBackgroundColor(Color.parseColor("#FFDC2727"))
                    itemView.tvAnswer.setTextColor(Color.parseColor("#ffffff"))
                }
                selected -> {
                    if (!alternativeSelected())
                        itemView.setBackgroundColor(Color.parseColor("#584B53"))
                        itemView.tvAnswer.setTextColor(Color.parseColor("#ffffff"))
                }
                else -> {
                    itemView.setBackgroundColor(Color.argb(200,243, 243, 243))
                    itemView.tvAnswer.setTextColor(Color.parseColor("#584B53"))
                }
            }
            itemView.setOnClickListener {
                selected = answer
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_answer
    }

    fun alternativeSelected() : Boolean {
        return correct != null
    }

    fun getSelectedItem(): Answer? {
        return selected
    }

    fun setCorrect(answer: Answer) {
        this.correct = answer
        this.selected = null
        notifyDataSetChanged()
    }

    fun setError(answer: Answer) {
        this.error = answer
        this.selected = null
        notifyDataSetChanged()
    }
}