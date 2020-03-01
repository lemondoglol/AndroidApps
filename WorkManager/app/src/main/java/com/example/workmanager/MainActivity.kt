package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.textView)

        /**
         * Create request
         * */
        val request = OneTimeWorkRequest.Builder(MyWorker::class.java).build()

        button.setOnClickListener {
            /**
             * put into request queue
             * */
            WorkManager.getInstance(this).enqueue(request)
        }

        /**
         * Live Data
         * */
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this,
            Observer<WorkInfo> {
                textView.append(it?.state?.name + "\n")
            }
            )
    }
}
