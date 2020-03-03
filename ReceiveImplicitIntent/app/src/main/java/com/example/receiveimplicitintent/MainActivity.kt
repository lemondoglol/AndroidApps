package com.example.receiveimplicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // this is getIntent
        val intent = intent
        val uri = intent.data
        if (uri != null) {
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show()
        }

        findViewById<TextView>(R.id.textView).setOnClickListener {
            testView(findViewById<TextView>(R.id.textView))
        }

    }

    fun testView(view: View) {
        Toast.makeText(this, "Test view", Toast.LENGTH_SHORT).show()

    }
}
