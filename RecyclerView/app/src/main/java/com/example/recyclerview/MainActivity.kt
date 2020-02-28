package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.my_recyclerView)
        val myAdapter = MyAdapter(this, getData())
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun getData(): ArrayList<String>{
        val data = ArrayList<String>()
        for (i in 1..15) {
            data.add("hello")
        }
        return data
    }

}
