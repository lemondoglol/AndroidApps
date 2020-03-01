package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var editText: TextView
    lateinit var saveButton: Button
    lateinit var getButton: Button
    lateinit var textView: TextView
    /**
     * Create DB
     * */
    lateinit var db: AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        saveButton = findViewById(R.id.save_button)
        getButton = findViewById(R.id.get_button)
        textView = findViewById(R.id.textView)


        db = Room.databaseBuilder(applicationContext, AppDB::class.java, "LemonDB").build()

        saveButton.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                saveToDB()
            }
        }

        getButton.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                getData()
            }
        }

    }

    suspend fun saveToDB() {
        withContext(Dispatchers.Default) {
            var item = ItemEntity()
            item.item_id = editText.text.toString().toInt()
            item.item_message = "Lemon with ID ${item.item_id}"
            item.item_name = "Ryan ${item.item_id}"
            db.itemsDao().saveItem(item)
        }
    }

    suspend fun getData() {
        var res = ""
        withContext(Dispatchers.Default) {
            db.itemsDao().getItems().forEach {
                res += it.item_name + " " + it.item_message + "\n"
            }
        }

        withContext(Dispatchers.Main) {
            textView.text = res
        }
    }
}
