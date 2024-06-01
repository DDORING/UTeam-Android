package com.neordi6th.ddoring.repository.member

import com.neordi6th.ddoring.data.network.RetrofitClient
import com.neordi6th.ddoring.data.service.MemberService
import okhttp3.MultipartBody

class MemberRepository {
    private val memberService = RetrofitClient.getLoggedInInstance().create(MemberService::class.java)

    suspend fun uploadCharacter(name: String, photo: MultipartBody.Part) =
        memberService.uploadCharacter(name, photo)

    suspend fun getMemberInfo() = memberService.getMemberInfo()

}