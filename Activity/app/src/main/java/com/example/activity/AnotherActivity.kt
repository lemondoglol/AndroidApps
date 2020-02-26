package com.example.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AnotherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        val yesButton: Button = findViewById(R.id.yes_button)
        val noButton: Button = findViewById(R.id.no_button)

        yesButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("message", "message from AnotherActivity")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        noButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
