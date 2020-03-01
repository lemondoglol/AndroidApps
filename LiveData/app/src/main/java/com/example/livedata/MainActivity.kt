package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.textView)

        /**
         * @param ViewModelProvider
         * */
        val model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        /**
         * set up observer
         * */
        model.currentInput.observe(this, Observer<String> {
            textView.text = it
            Toast.makeText(this, "Data has been changed", Toast.LENGTH_SHORT).show()
        })

        // Test
        button.setOnClickListener {
            val randomNumber = (0..10).random()
            model.currentInput.value = "The input is $randomNumber"
        }

    }
}
