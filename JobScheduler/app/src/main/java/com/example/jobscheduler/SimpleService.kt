package com.example.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.provider.AlarmClock


/**
 * @property JobService
 * */
class SimpleService: JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER)
        intent.putExtra(AlarmClock.EXTRA_LENGTH, 1)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

}