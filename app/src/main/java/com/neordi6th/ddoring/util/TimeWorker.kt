package com.neordi6th.ddoring.util

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters

class TimerWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val totalTimeInMinutes = inputData.getLong("totalTimeInMinutes", 0)
        val intent = Intent("TIMER_UPDATED")
        for (i in totalTimeInMinutes downTo 1) {
            Thread.sleep(60000) // 1분 대기
            intent.putExtra("remainingTime", i - 1)
            applicationContext.sendBroadcast(intent)
        }
        return Result.success()
    }
}