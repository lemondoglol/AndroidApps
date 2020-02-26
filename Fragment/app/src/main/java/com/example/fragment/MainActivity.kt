package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.display_button)
        val names = ArrayList<String>()
        names.add("Ryan")
        names.add("Lemon")
        names.add("Tuna")


        button.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
//            transaction.add(R.id.fragment, MyFragment(names))
            transaction.replace(R.id.fragment, MyFragment(names))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
