package com.neordi6th.ddoring.data.service

import com.neordi6th.ddoring.data.dto.MemberResponse
import com.neordi6th.ddoring.data.dto.UploadRequest
import com.neordi6th.ddoring.data.dto.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MemberService {
    @Multipart
    @POST("person/upload/}")
    suspend fun uploadCharacter(
        @Query("name") name: String,
        @Part photo: MultipartBody.Part
    ): Response<UploadResponse>

    @GET("person/get/{memberId}")
    suspend fun getMemberInfo(): Response<MemberResponse>
}