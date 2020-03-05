package com.example.jobscheduler

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import android.app.PendingIntent



class AlarmReceiver : BroadcastReceiver() {

    lateinit var notificationManager: NotificationManager
    val channelID = "com.example.jobscheduler"  // same as Notification

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        deliverNotification(context)
    }



    private fun deliverNotification(context: Context) {
        val contentIntent = Intent(context, MainActivity::class.java)

        val contentPendingIntent = PendingIntent.getActivity(
            context, 25, contentIntent, PendingIntent
                .FLAG_UPDATE_CURRENT
        )
        // Build the notification
        val builder = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Notification Title")
            .setContentText("Wait Up")
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        // Deliver the notification
        notificationManager.notify(25, builder.build())
    }
}
