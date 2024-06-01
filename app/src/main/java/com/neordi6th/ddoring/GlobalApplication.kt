package com.neordi6th.ddoring

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.kakao.sdk.common.KakaoSdk
import com.neordi6th.ddoring.util.UserPrefs

class GlobalApplication : Application() {
    companion object {
        lateinit var userPrefs: UserPrefs
    }

    override fun onCreate() {
        userPrefs = UserPrefs(applicationContext)
        super.onCreate()
        KakaoSdk.init(this, "")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "my_channel_id",
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My channel description"
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}