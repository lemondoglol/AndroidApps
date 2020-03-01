package com.example.workmanager

import android.content.Context
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {

    /**
     * It has to run inside a scope
     * */
    override fun doWork(): Result {
        return try {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "This is a message", Toast.LENGTH_LONG).show() }
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }

}
