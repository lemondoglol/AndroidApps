package com.example.sharedpreferencesappsettings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText

    lateinit var sharedPreferences: SharedPreferences
    val sharedPreFile: String = "com.example.sharedpreferencesappsettings.sharedPreFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)

        sharedPreferences = getSharedPreferences(sharedPreFile, Context.MODE_PRIVATE)

        /**
         * Retrieve SharedPreference
         * */
        textView.text = sharedPreferences.getString("Message", "Default Value")
        textView.contentDescription = textView.text.toString() + "Hello WOrld"
    }

    override fun onPause() {
        super.onPause()
        /**
         * save sharedPreference
         * */
        val preferencesEditor = sharedPreferences.edit()
        preferencesEditor.putString("Message", editText.text.toString())
        preferencesEditor.apply()
    }
}
