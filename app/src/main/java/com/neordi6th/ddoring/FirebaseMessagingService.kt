package com.neordi6th.ddoring

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.neordi6th.ddoring.data.dto.NotificationRequest
import com.neordi6th.ddoring.data.network.RetrofitClient
import com.neordi6th.ddoring.data.service.NotificationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FCMFCM"
    private val notificationService =
        RetrofitClient.getInstance().create(NotificationService::class.java)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        saveTokenToServer(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        // 수신한 메세지 처리
        // notification이 존재하면 앱이 백그라운드에 있을 때 onMessageReceived에서 제어 불가. 따라서 message.data에 모든 정보 존재
        super.onMessageReceived(message)
        Log.d(TAG, "onMessageReceived: $message")
    }

    private fun saveTokenToServer(token: String) {

        notificationService.saveToken(NotificationRequest(token, "test", "test")).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Token successfully saved to server.")
                } else {
                    Log.e(TAG, "Failed to save token to server. Response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Error saving token to server: ${t.localizedMessage}")
            }
        })
    }

}