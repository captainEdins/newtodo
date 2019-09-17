package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.Data.personal
import com.example.todo.R


class CustomAdapter(val personalList: ArrayList<personal>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.personal, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return personalList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p_personal:personal = personalList[position]
        holder.textView.text = p_personal.place
        holder.textView2.text = p_personal.time
        holder.textView3.text = p_personal.about

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView) as TextView
        val textView2 = itemView.findViewById<TextView>(R.id.textView2) as TextView
        val textView3 = itemView.findViewById<TextView>(R.id.textView3) as TextView
    }
}