package com.neordi6th.ddoring.data.service

import com.neordi6th.ddoring.data.dto.NotificationRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationService {
    @POST("api/v1/fcm/send")
    fun saveToken(@Body notificationRequest: NotificationRequest): Call<String>
}