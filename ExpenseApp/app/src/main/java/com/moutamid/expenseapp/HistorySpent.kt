package com.moutamid.expenseapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class HistorySpent(var context: Context, var list: ArrayList<Model?>?) :
    RecyclerView.Adapter<HistorySpent.HistoryVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        return HistoryVH(LayoutInflater.from(context).inflate(R.layout.history_item, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        val model = list!![holder.adapterPosition]
        holder.name.text = model?.name
        if (model!!.isExpense) {
            holder.isIncome.text = "Spent"
            holder.price.text = "$" + String.format("%,.2f", model.price)
            holder.icon.setImageResource(R.drawable.round_remove_24)
            holder.price.setTextColor(context.resources.getColor(R.color.spent))
        } else {
            holder.isIncome.text = "Income"
            holder.price.text = "$" + String.format("%,.2f", model.price)
            holder.icon.setImageResource(R.drawable.add)
            holder.price.setTextColor(context.resources.getColor(R.color.income))
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class HistoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isIncome: TextView
        var name: TextView
        var price: TextView
        var icon: ImageView

        init {
            isIncome = itemView.findViewById(R.id.isIncome)
            name = itemView.findViewById(R.id.name)
            price = itemView.findViewById(R.id.price)
            icon = itemView.findViewById(R.id.icon)
        }
    }
}