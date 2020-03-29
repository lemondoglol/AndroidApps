package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(context: Context, private val data: ArrayList<String>)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    /**
     * This lay will be same
     * */
//    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * almost same
     * @param R.layout.item_layout the layout for each item (LinearLayout)
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val itemView: View = inflater.inflate(R.layout.item_layout, parent, false)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.linearlayout, parent, false)
        return MyViewHolder(itemView, this)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * pass data to ViewHolder
     * */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        getItem(po)
        val currentData = data[position]
        holder.textView.text = currentData
    }


    /**
     * ViewHolder for each element
     * */
    class MyViewHolder(itemView: View, private val adapter: MyAdapter)
        : RecyclerView.ViewHolder(itemView) {

        val textView: TextView = itemView.findViewById(R.id.item_textView)

        init {
            textView.setOnClickListener {
                val position = layoutPosition
                adapter.data[position] = "Clicked " + adapter.data[position]
                adapter.notifyDataSetChanged()
            }
        }
    }

}