package com.neordi6th.ddoring.util

import android.content.Context
import android.content.SharedPreferences
import com.kakao.sdk.auth.Constants.ACCESS_TOKEN
import com.kakao.sdk.auth.Constants.REFRESH_TOKEN

class UserPrefs(context: Context) {
    private var sharedPreference: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        editor = sharedPreference.edit()
    }

    fun setAccessToken(value: String) {
        editor.putString("AccessToken", value).apply()
    }

    fun getAccessToken(defValue: String) = sharedPreference.getString("AccessToken", defValue)

    fun setRefreshToken(value: String) {
        editor.putString("RefreshToken", value).apply()
    }

    fun getRefreshToken(defValue: String) = sharedPreference.getString("RefreshToken", defValue)

}