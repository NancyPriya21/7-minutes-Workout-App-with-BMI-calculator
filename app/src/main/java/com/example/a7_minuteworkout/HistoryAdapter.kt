package com.example.a7_minuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7_minuteworkout.databinding.ItemHistoryDateBinding

class HistoryAdapter(private val items: ArrayList<String>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryDateBinding): RecyclerView.ViewHolder(binding.root){
        val llHistoryItem = binding.llHistoryItemMain
        val serialNo= binding.tvPosition
        val tvdate =binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryDateBinding.inflate(LayoutInflater.from(parent.context), parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String=items[position]
        holder.serialNo.text = (position+1).toString()
        holder.tvdate.text = item

        if(position%2 ==0){
            holder.llHistoryItem.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                R.color.lightGrey))
        }
        else{
            holder.llHistoryItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

        override fun getItemCount(): Int {
        return items.size
    }
}