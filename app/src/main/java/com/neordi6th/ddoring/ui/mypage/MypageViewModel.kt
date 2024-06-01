package com.neordi6th.ddoring.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neordi6th.ddoring.data.dto.MemberResponse
import com.neordi6th.ddoring.repository.member.MemberRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class MypageViewModel : ViewModel() {

    private val memberRepository = MemberRepository()

    private val _memberInfo = MutableLiveData<MemberResponse>()
    val memberInfo: LiveData<MemberResponse> = _memberInfo

    fun getMemberInfo() {
        viewModelScope.launch {
            val response = memberRepository.getMemberInfo()
            if (response.isSuccessful) {
                _memberInfo.value = response.body()
            } else {
                _memberInfo.value = MemberResponse("", "")
            }
        }
    }

    fun updateProfile(photo: MultipartBody.Part) {
        viewModelScope.launch {
            val response = memberRepository.uploadCharacter("name", photo)

        }
    }

}