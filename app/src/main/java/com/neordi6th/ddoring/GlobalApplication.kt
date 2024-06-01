package com.neordi6th.ddoring

import android.app.Application
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
    }
}