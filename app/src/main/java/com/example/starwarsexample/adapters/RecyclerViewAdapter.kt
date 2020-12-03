package com.example.starwarsexample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsexample.R
import com.example.starwarsexample.pojos.People
import javax.inject.Inject


class RecyclerViewAdapter @Inject constructor( val clickListener: ClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val data: MutableList<People>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_list_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = data[position].name
        holder.txtBirthYear.text = data[position].birthYear
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtBirthYear: TextView = itemView.findViewById(R.id.txtBirthYear)
        private val constraintLayoutContainer: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)

        init {
            constraintLayoutContainer.setOnClickListener {
                clickListener.launchIntent(
                    data[adapterPosition].films?.get(
                        0
                    )
                )
            }
        }
    }

    interface ClickListener {
        fun launchIntent(filmName: String?)
    }

    fun setData(data: List<People>?) {
        this.data.addAll(data!!)
        notifyDataSetChanged()
    }

    init {
        data = ArrayList()
    }
}