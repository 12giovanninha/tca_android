package com.example.tca.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tca.R
import com.example.tca.dao.CategoryDAO
import com.example.tca.models.category.OutCategory
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(view: View): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selected : OutCategory? = null
    private val dao: CategoryDAO = CategoryDAO()
    private var outCategories: MutableList<OutCategory> = mutableListOf()


    init {
        dao.findAll { categoryApi ->
            outCategories = categoryApi.toMutableList()
            notifyDataSetChanged()
            view.set.visibility = View.VISIBLE
            view.choose.visibility = View.VISIBLE
            view.btSave.visibility = View.VISIBLE
            view.pg.visibility = View.GONE

        }
    }
    override fun getItemCount() = outCategories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fillView(outCategories[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(outCategory: OutCategory, position: Int) {
            itemView.tvCategoryName.text = (position + 1).toString()+ ")  " + outCategory.name.toUpperCase()

            itemView.setOnClickListener {
                selected = outCategory
                notifyDataSetChanged()
            }
        }
    }

    fun getSelectedItem(): OutCategory? {
        return selected
    }

    override fun getItemViewType(position: Int): Int {
        if (outCategories[position] == selected) {
            return R.layout.item_category_selected
        }
        return R.layout.item_category
    }
}