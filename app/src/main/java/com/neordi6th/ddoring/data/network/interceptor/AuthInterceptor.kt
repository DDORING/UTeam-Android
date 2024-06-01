package com.neordi6th.ddoring.data.network.interceptor

import android.util.Log
import com.neordi6th.ddoring.GlobalApplication
import com.neordi6th.ddoring.data.dto.LoginResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor() : Interceptor {

//    private val retrofitClient: AuthService by lazy {
//        RetrofitClient.getInstance().create(AuthService::class.java)
//    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = GlobalApplication.userPrefs.getAccessToken(DEFAULT_VALUE)
        val request = chain.request().putTokenHeader(getAccessToken()!!)
        val response = chain.proceed(request)

        // Access Token이 존재하지 않을경우 해당 응답 반환
        if (accessToken.isNullOrEmpty()) {
            return response
        }

        Log.d("Response Code: ", "intercept: ${response.code} ${response.message}")

        // Access Token이 만료되었을 경우 Refresh Token을 이용하여 Access Token 재발급

        if (response.code == TOKEN_EXPIRED_RESPONSE_CODE) {
            val newAccessToken = getNewAccessToken()
            newAccessToken?.let {
                // 새로운 엑세스 토큰을 헤더에 추가한 새로운 Request 생성
                val newRequest = request.putTokenHeader(newAccessToken.accessToken)
                GlobalApplication.userPrefs.setAccessToken(newAccessToken.accessToken)
                GlobalApplication.userPrefs.setRefreshToken(newAccessToken.refreshToken)
                // 새로운 Request로 다시 API 요청
                return chain.proceed(newRequest)
            }
        }
        // 401이 아닌 경우 현재의 응답 반환
        return response
    }

    private fun Request.putTokenHeader(accessToken: String): Request {
        return this.newBuilder()
            .addHeader(AUTHORIZATION, "Bearer $accessToken")
            .build()
    }

    private fun getNewAccessToken(): LoginResponse? {
        return runBlocking {
            val response = retrofitClient.getAccessToken("Bearer ${getRefreshToken()}")
            if (response.isSuccessful) {
                return@runBlocking response.body()!!.data
            }
            return@runBlocking null
        }
    }

    private fun getAccessToken() = GlobalApplication.userPrefs.getAccessToken(DEFAULT_VALUE)
    private fun getRefreshToken() = GlobalApplication.userPrefs.getRefreshToken(DEFAULT_VALUE)

    companion object {
        private const val DEFAULT_VALUE = ""
        private const val TOKEN_EXPIRED_RESPONSE_CODE = 401
        private const val AUTHORIZATION = "Authorization"
    }
}