package com.example.jobscheduler

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scheduleButton: Button = findViewById(R.id.schedule_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)
        val notificationButton: Button = findViewById(R.id.notification_button)


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

        /******************Notification Part*************************/
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "com.example.jobscheduler" // Unique

        notificationButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel =
                    NotificationChannel(channelID, "description", NotificationManager.IMPORTANCE_HIGH)
                channel.enableLights(true)
                channel.enableVibration(true)
                notificationManager.createNotificationChannel(channel)

                // create intent
                val intent = Intent(this, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT)

                val notificationBuilder = Notification.Builder(this, channelID)
                    .setContentTitle("Here is the title")
                    .setContentText("Text")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)

                // this id will be used to cancel this notification
                notificationManager.notify(25, notificationBuilder.build())

            } else {
                TODO("VERSION.SDK_INT < O")
            }
        }


    }
}
