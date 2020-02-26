package com.example.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton: Button = findViewById(R.id.sendIntent_button)
        val displayView: TextView = findViewById(R.id.displayView)

        sendButton.setOnClickListener {
            val intent = Intent(this, AnotherActivity::class.java)
            intent.putExtra("message", displayView.text)

            startActivityForResult(intent, 25)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 25 && resultCode == Activity.RESULT_OK) {
            val res = data?.getStringExtra("message")
            Toast.makeText(this, res, Toast.LENGTH_LONG).show()
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show()
        }
    }
}
