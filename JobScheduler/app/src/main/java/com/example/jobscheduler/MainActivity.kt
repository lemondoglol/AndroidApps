package com.example.jobscheduler

import android.app.*
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.ToggleButton
import android.app.PendingIntent
import android.app.NotificationManager
import android.app.NotificationChannel

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var channelID: String

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
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        channelID = "com.example.jobscheduler" // Unique

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
            }
        }



        val toggle: ToggleButton = findViewById(R.id.toggleButton)
        /**
         * Create Intent for AlarmReceiver
         * */
        val intent = Intent(this, AlarmReceiver::class.java)

        /**
         * !!! getBroadcast
         * */
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)


        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
//                val repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES
                val repeatInterval: Long = 5*1000 // 5 seconds
                val triggerTime: Long = SystemClock.elapsedRealtime() + repeatInterval
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    repeatInterval,
                    pendingIntent)
            }
        }

        createNotificationChannel()
    }


    /**
     * Creates a Notification channel, for OREO and higher.
     */
    private fun createNotificationChannel() {

        // Create a notification manager object.
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                channelID,
                "Stand up notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifies every 15 minutes to " + "stand up and walk"
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
