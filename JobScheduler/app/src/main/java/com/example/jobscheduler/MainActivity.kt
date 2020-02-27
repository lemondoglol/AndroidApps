package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scheduleButton: Button = findViewById(R.id.schedule_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)



        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        scheduleButton.setOnClickListener {
            val serviceName = ComponentName(this, SimpleService::class.java)
            // jobId used to cancel this job
            val builder = JobInfo.Builder(25, serviceName)
            builder.setMinimumLatency(2000)
            jobScheduler.schedule(builder.build())
        }

        cancelButton.setOnClickListener {
            jobScheduler.cancel(25)
        }

    }
}
