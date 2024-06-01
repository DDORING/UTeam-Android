package com.neordi6th.ddoring.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private const val baseUrl = "http://" + "8080/"
    private val gson: Gson = GsonBuilder().setLenient().create()

    private fun createRetrofitBuilder(
        baseUrl: String,
        client: OkHttpClient? = null
    ): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .apply { client?.let { client(it) } }

}
